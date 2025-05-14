package com.korosoft.TribalWarsRemake.domain.event.attack;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEventEntity;
import com.korosoft.TribalWarsRemake.domain.event.EventType;
import com.korosoft.TribalWarsRemake.domain.event.EventProcessor;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import org.springframework.stereotype.Component;

@Component
class AttackEventProcessor implements EventProcessor<AttackEvent> {

    @Override
    public void processEvent(AttackEvent event, Player player) {
        // TODO implement battle module
    }

    @Override
    public EventType getEventType() {
        return EventType.ATTACK;
    }
}
