package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.event.dto.AbstractEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.SupportEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.player.repository.PlayerRepository;
import com.korosoft.TribalWarsRemake.domain.resources.ResourcesFacade;
import com.korosoft.TribalWarsRemake.domain.time.FinishTimeFacade;
import com.korosoft.TribalWarsRemake.domain.village.Village;
import com.korosoft.TribalWarsRemake.domain.village.VillageFacade;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EventServiceFacade {

    private final ResourcesFacade resourcesFacade;
    private final VillageFacade villageFacade;
    private final EventQueryServiceImpl eventQueryService;
    private final PlayerRepository playerRepository;
    private final Map<EventType, EventProcessor> processEventServiceMap;
    private final FinishTimeFacade finishTimeFacade;
    private final Clock clock;

    @Autowired
    EventServiceFacade(ResourcesFacade resourcesFacade, VillageFacade villageFacade, EventQueryServiceImpl eventQueryService,
                       PlayerRepository playerRepository, List<EventProcessor> eventProcessorList, FinishTimeFacade finishTimeFacade, Clock clock) {
        this.eventQueryService = eventQueryService;
        this.playerRepository = playerRepository;
        this.processEventServiceMap = eventProcessorList.stream().collect(Collectors.toMap(EventProcessor::getEventType, Function.identity()));
        this.clock = clock;
        this.resourcesFacade = resourcesFacade;
        this.finishTimeFacade = finishTimeFacade;
        this.villageFacade = villageFacade;
    }

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional
    public void processEvents(int playerId) {
        Instant now = clock.instant();
        PriorityQueue<AbstractEvent> queue = this.gatherEvents(playerId, now);
        Optional<Player> playerOptional = this.playerRepository.findById(playerId);
        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            this.processEvents(queue, player);
            this.eventQueryService.deleteEvents(playerId, now);
            return;
        }
        //TODO move it to logs instead
        System.out.println("No player found with id " + playerId);
    }

    @Transactional
    public void createAttackEvent(AttackEventDto attackEventDto) {
        Instant now = this.clock.instant();
        this.setTimesInEvent(attackEventDto, EventType.ATTACK, now);
        Village village = this.villageFacade.getVillage(attackEventDto.getTargetVillageId());
        this.resourcesFacade.updateResources(village, now);
        //TODO unmock player ID
        this.processEvents(5);
        this.eventQueryService.addAttackEvent(attackEventDto, now);
    }

    @Transactional
    public void createSupportEvent(SupportEventDto supportEventDto) {
        Instant now = this.clock.instant();
        this.setTimesInEvent(supportEventDto, EventType.SUPPORT, now);
        Village village = this.villageFacade.getVillage(supportEventDto.getTargetVillageId());
        this.resourcesFacade.updateResources(village, now);
        //TODO unmock player ID
        this.processEvents(5);
        this.eventQueryService.addSupportEvent(supportEventDto, now);
    }

    @Transactional
    public void createTransportEvent(TransportEventDto transportEventDto) {
        Instant now = this.clock.instant();
        this.setTimesInEvent(transportEventDto, EventType.TRANSPORT, now);
        Village village = this.villageFacade.getVillage(transportEventDto.getTargetVillageId());
        this.resourcesFacade.updateResources(village, now);
        // TODO remove resources from village and check if there are enough resources to send
        // TODO process events before validating for resources
        this.eventQueryService.addTransportEvent(transportEventDto, now);
    }

    private void setTimesInEvent(AbstractEventDto abstractEventDto, EventType eventType, Instant now) {
        Instant finishTime = this.finishTimeFacade.getTravelTime(now, eventType);
        abstractEventDto.setStartTime(now);
        abstractEventDto.setEndTime(finishTime);
    }

    private PriorityQueue<AbstractEvent> gatherEvents(int playerId, Instant timestamp) {
        PriorityQueue<AbstractEvent> queue = new PriorityQueue<>(this.getEventsComparator());
        queue.addAll(this.eventQueryService.getAllEventsToProcess(playerId, timestamp));
        return queue;
    }

    @SuppressWarnings("unchecked")
    private void processEvents(PriorityQueue<AbstractEvent> queue, Player player) {
        while (queue.peek() != null) {
            AbstractEvent event = queue.poll();
            this.processEventServiceMap.get(event.eventRoot.getEventType()).processEvent(event, player);
            System.out.println("Processed " + event.eventRoot.getEventType() + " eventId: " + event.eventRoot.getId());
        }
        this.playerRepository.save(player);
    }

    /**
     * Context for suppression: Priority queue by default inserts "same" objects arbirarily, and we need a deterministic result.
     * In case of our events, the only thing that matters in processing is timestamp at which they were created.
     * If in some case the timestamp will be the same, we need to break the tie in a clear manner.
     * TODO think of a way to break these ties. Village id? Player id? Event type?
     * @return Comparator for priority queue used to process events
     */
    @SuppressWarnings("all")
    private Comparator<AbstractEvent> getEventsComparator() {
        return (event1, event2) -> {
            if (event1.eventRoot.getFinishDate().isBefore(event2.eventRoot.getFinishDate())) {
                return 1;
            } else {
                return -1;
            }
        };
    }
}
