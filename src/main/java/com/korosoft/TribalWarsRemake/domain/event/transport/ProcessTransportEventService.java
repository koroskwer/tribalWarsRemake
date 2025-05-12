package com.korosoft.TribalWarsRemake.domain.event.transport;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEventEntity;
import com.korosoft.TribalWarsRemake.domain.event.EventType;
import com.korosoft.TribalWarsRemake.domain.event.ProcessEventService;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import org.springframework.stereotype.Component;

@Component
class ProcessTransportEventService implements ProcessEventService {

    @Override
    public void processEvent(AbstractEventEntity event, Player player) {

    }

    @Override
    public EventType getEventType() {
        return EventType.TRANSPORT;
    }
}
