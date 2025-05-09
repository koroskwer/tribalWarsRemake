package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.player.Player;
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

    public void processEvents(Player player) {
        Queue<AbstractEvent> queue = this.gatherEvents(player.getId());
        this.processEvents(queue);
    }

    private Queue<AbstractEvent> gatherEvents(int playerId) {
        PriorityQueue<AbstractEvent> queue = new PriorityQueue<>(this.getComparator());
        queue.addAll(this.eventQueryService.getAllEventsToProcess(playerId));
        return queue;
    }

    private void processEvents(Queue<AbstractEvent> queue) {
        queue.forEach((event) -> this.processEventServiceMap.get(event.getEventType()).processEvent(event));
    }

    private Comparator<AbstractEvent> getComparator() {
        return (event1, event2) -> {
            if (event1.getFinishDate().isBefore(event2.getFinishDate())) {
                return 1;
            } else {
                return -1;
            }
        };
    }
}
