package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.player.Player;
import org.springframework.stereotype.Component;

@Component
class ProcessSupportEventService implements ProcessEventService {

    @Override
    public void processEvent(AbstractEvent event, Player player) {

    }

    @Override
    public EventType getEventType() {
        return EventType.SUPPORT;
    }
}
