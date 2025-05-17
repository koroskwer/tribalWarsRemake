package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.SupportEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.player.repository.PlayerRepository;
import com.korosoft.TribalWarsRemake.domain.resources.ResourcesFacade;
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
    private final Clock clock;

    @Autowired
    EventServiceFacade(ResourcesFacade resourcesFacade, VillageFacade villageFacade, EventQueryServiceImpl eventQueryService,
                       PlayerRepository playerRepository, List<EventProcessor> eventProcessorList, Clock clock) {
        this.eventQueryService = eventQueryService;
        this.playerRepository = playerRepository;
        this.processEventServiceMap = eventProcessorList.stream().collect(Collectors.toMap(EventProcessor::getEventType, Function.identity()));
        this.clock = clock;
        this.resourcesFacade = resourcesFacade;
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
            this.deleteProcessedEvents(playerId, now);
            return;
        }
        //TODO move it to logs instead
        System.out.println("No player found with id " + playerId);
    }

    private void deleteProcessedEvents(int playerId, Instant now) {
        this.eventQueryService.deleteEvents(playerId, now);
    }

    public void createAttackEvent(AttackEventDto attackEventDto) {
        Instant now = clock.instant();
        Village village = this.villageFacade.getVillage(attackEventDto.getTargetVillageId());
        this.resourcesFacade.updateResources(village, now);
        // TODO remove army from village and validate if enough troops are present to send the attack
        // TODO process events before validating for resources
        this.eventQueryService.addAttackEvent(attackEventDto, now);
    }

    public void createSupportEvent(SupportEventDto supportEventDto) {
        Instant now = clock.instant();
        Village village = this.villageFacade.getVillage(supportEventDto.getTargetVillageId());
        this.resourcesFacade.updateResources(village, now);
        // TODO remove army from village and validate if enough troops are present to send the support
        // TODO process events before validating for resources
        this.eventQueryService.addSupportEvent(supportEventDto, now);
    }

    public void createTransportEvent(TransportEventDto transportEventDto) {
        Instant now = clock.instant();
        Village village = this.villageFacade.getVillage(transportEventDto.getTargetVillageId());
        this.resourcesFacade.updateResources(village, now);
        // TODO remove resources from village and check if there are enough resources to send
        // TODO process events before validating for resources
        this.eventQueryService.addTransportEvent(transportEventDto, now);
    }

    private PriorityQueue<AbstractEvent> gatherEvents(int playerId, Instant timestamp) {
        PriorityQueue<AbstractEvent> queue = new PriorityQueue<>(this.getEventsComparator());
        queue.addAll(this.eventQueryService.getAllEventsToProcess(playerId, timestamp));
        return queue;
    }

    private void processEvents(Queue<AbstractEvent> queue, Player player) {
        Iterator<AbstractEvent> iterator = queue.iterator();
        while (iterator.hasNext()) {
            AbstractEvent event = iterator.next();
            this.processEventServiceMap.get(event.eventRoot.getEventType()).processEvent(event, player);
            iterator.remove();
        }
        this.playerRepository.save(player);
    }

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
