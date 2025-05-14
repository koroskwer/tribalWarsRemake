package com.korosoft.TribalWarsRemake.domain.event.transport;

import com.korosoft.TribalWarsRemake.domain.event.EventType;
import com.korosoft.TribalWarsRemake.domain.event.EventProcessor;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import org.springframework.stereotype.Component;

@Component
class TransportEventProcessor implements EventProcessor<TransportEvent> {

    @Override
    public void processEvent(TransportEvent event, Player player) {

    }

    @Override
    public EventType getEventType() {
        return EventType.TRANSPORT;
    }
}
