package com.korosoft.TribalWarsRemake.domain.event.transport;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEventEntity;
import com.korosoft.TribalWarsRemake.domain.event.EventType;
import com.korosoft.TribalWarsRemake.domain.event.EventProcessor;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import org.springframework.stereotype.Component;

@Component
class TransportEventProcessor implements EventProcessor {

    @Override
    public void processEvent(AbstractEventEntity event, Player player) {

    }

    @Override
    public EventType getEventType() {
        return EventType.TRANSPORT;
    }
}
