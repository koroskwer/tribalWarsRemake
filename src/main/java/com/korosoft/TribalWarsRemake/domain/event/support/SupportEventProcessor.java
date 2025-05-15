package com.korosoft.TribalWarsRemake.domain.event.support;

import com.korosoft.TribalWarsRemake.domain.event.EventProcessor;
import com.korosoft.TribalWarsRemake.domain.event.EventType;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import org.springframework.stereotype.Component;

@Component
class SupportEventProcessor implements EventProcessor<SupportEvent> {

    @Override
    public void processEvent(SupportEvent event, Player player) {
        event.getTargetVillage().getArmies().add(event.getArmy());
    }

    @Override
    public EventType getEventType() {
        return EventType.SUPPORT;
    }
}
