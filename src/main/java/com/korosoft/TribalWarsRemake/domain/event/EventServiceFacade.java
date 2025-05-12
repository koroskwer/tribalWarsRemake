package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.SupportEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.player.repository.PlayerRepository;
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

    private final EventQueryServiceImpl eventQueryService;
    private final PlayerRepository playerRepository;
    private final Map<EventType, ProcessEventService> processEventServiceMap;
    private final Clock clock;

    @Autowired
    EventServiceFacade(EventQueryServiceImpl eventQueryService, PlayerRepository playerRepository, List<ProcessEventService> processEventServiceList, Clock clock) {
        this.eventQueryService = eventQueryService;
        this.playerRepository = playerRepository;
        this.processEventServiceMap = processEventServiceList.stream().collect(Collectors.toMap(ProcessEventService::getEventType, Function.identity()));
        this.clock = clock;
    }

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional
    public void processEvents(int playerId) {
        Instant now = clock.instant();
        PriorityQueue<AbstractEvent> queue = this.gatherEvents(playerId, now);
        Player player = this.playerRepository.findById(playerId).get();
        this.processEvents(queue, player);
        this.deleteProcessedEvents();
    }

    private void deleteProcessedEvents() {
        //TODO implement deletion of processed events
    }

    public void createAttackEvent(AttackEventDto attackEventDto) {
        Instant now = clock.instant();
        this.eventQueryService.addAttackEvent(attackEventDto, now);
    }

    public void createSupportEvent(SupportEventDto supportEventDto) {
        Instant now = clock.instant();
        this.eventQueryService.addSupportEvent(supportEventDto, now);
    }

    public void createTransportEvent(TransportEventDto transportEventDto) {
        Instant now = clock.instant();
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
            this.processEventServiceMap.get(event.eventRoot.getEventType()).processEvent(event.getEventRoot(), player);
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
