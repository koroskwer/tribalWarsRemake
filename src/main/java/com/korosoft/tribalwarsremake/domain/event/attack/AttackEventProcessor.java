package com.korosoft.tribalwarsremake.domain.event.attack;

import com.korosoft.tribalwarsremake.domain.battle.BattleFacade;
import com.korosoft.tribalwarsremake.domain.event.EventType;
import com.korosoft.tribalwarsremake.domain.event.EventProcessor;
import com.korosoft.tribalwarsremake.domain.player.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class AttackEventProcessor implements EventProcessor<AttackEvent> {

    private final BattleFacade battleFacade;

    @Override
    public void processEvent(AttackEvent event, Player player) {
        this.battleFacade.runBattle(event.getTargetVillage(), event.getArmy());
    }

    @Override
    public EventType getEventType() {
        return EventType.ATTACK;
    }
}
