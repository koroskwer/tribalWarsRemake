package com.korosoft.tribalwarsremake.domain.event.attack;

import com.korosoft.tribalwarsremake.domain.event.EventType;
import com.korosoft.tribalwarsremake.domain.event.EventProcessor;
import com.korosoft.tribalwarsremake.domain.player.Player;
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
