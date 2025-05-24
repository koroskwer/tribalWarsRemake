package com.korosoft.tribalwarsremake.domain.event.transport;

import com.korosoft.tribalwarsremake.domain.event.EventType;
import com.korosoft.tribalwarsremake.domain.event.EventProcessor;
import com.korosoft.tribalwarsremake.domain.player.Player;
import com.korosoft.tribalwarsremake.domain.resources.Resources;
import com.korosoft.tribalwarsremake.domain.resources.ResourcesFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class TransportEventProcessor implements EventProcessor<TransportEvent> {

    private final ResourcesFacade resourcesFacade;

    @Override
    public void processEvent(TransportEvent event, Player player) {
        Resources mergedResources = this.resourcesFacade.mergeResources(event.getResources(), event.getTargetVillage().getResources());
        event.getTargetVillage().setResources(mergedResources);
    }

    @Override
    public EventType getEventType() {
        return EventType.TRANSPORT;
    }
}
