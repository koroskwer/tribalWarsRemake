package com.korosoft.TribalWarsRemake.domain.event.transport;

import com.korosoft.TribalWarsRemake.domain.event.EventType;
import com.korosoft.TribalWarsRemake.domain.event.EventProcessor;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.resources.Resources;
import com.korosoft.TribalWarsRemake.domain.resources.ResourcesFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class TransportEventProcessor implements EventProcessor<TransportEvent> {

    private final ResourcesFacade resourcesFacade;

    @Override
    public void processEvent(TransportEvent event, Player player) {
        Resources mergedResources = this.resourcesFacade.mergeResources(event.getResources(), event.getSourceVillage().getResources());
        event.getSourceVillage().setResources(mergedResources);
    }

    @Override
    public EventType getEventType() {
        return EventType.TRANSPORT;
    }
}
