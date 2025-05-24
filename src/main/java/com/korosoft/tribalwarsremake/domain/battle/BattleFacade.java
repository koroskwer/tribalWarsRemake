package com.korosoft.tribalwarsremake.domain.battle;

import com.korosoft.tribalwarsremake.domain.army.Army;
import com.korosoft.tribalwarsremake.domain.village.Village;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BattleFacade {

    private final BattleService battleService;

    public void runBattle(Village defendingVillage, Army attacker) {
        this.battleService.runBattle(defendingVillage, attacker);
    }
}
