package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.player.Player;
import org.springframework.stereotype.Component;

@Component
class ProcessAttackEventService implements ProcessEventService {

    @Override
    public void processEvent(AbstractEventEntity event, Player player) {
        // TODO implement battle module
    }

    @Override
    public EventType getEventType() {
        return EventType.ATTACK;
    }
}
