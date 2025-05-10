package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.SupportEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

@AllArgsConstructor
@Component
public class EventServiceFacade {

    private final EventQueryServiceImpl eventQueryService;
    private final Map<EventType, ProcessEventService> processEventServiceMap;

    public void processEvents(int playerId) {
        PriorityQueue<AbstractEvent> queue = this.gatherEvents(playerId);
        this.processEvents(queue);
    }

    public void createAttackEvent(AttackEventDto attackEventDto) {

    }

    public void createSupportEvent(SupportEventDto supportEventDto) {

    }

    public void createTransportEvent(TransportEventDto transportEventDto) {

    }

    private PriorityQueue<AbstractEvent> gatherEvents(int playerId) {
        PriorityQueue<AbstractEvent> queue = new PriorityQueue<>(this.getEventsComparator());
        queue.addAll(this.eventQueryService.getAllEventsToProcess(playerId));
        return queue;
    }

    private void processEvents(Queue<AbstractEvent> queue) {
        queue.forEach((event) -> this.processEventServiceMap.get(event.getEventType()).processEvent(event));
    }

    private Comparator<AbstractEvent> getEventsComparator() {
        return (event1, event2) -> {
            if (event1.getFinishDate().isBefore(event2.getFinishDate())) {
                return 1;
            } else {
                return -1;
            }
        };
    }
}
