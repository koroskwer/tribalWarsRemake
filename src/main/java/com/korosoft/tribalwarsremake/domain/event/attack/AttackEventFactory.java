package com.korosoft.tribalwarsremake.domain.event.attack;

import com.korosoft.tribalwarsremake.domain.army.Army;
import com.korosoft.tribalwarsremake.domain.army.ArmyFacade;
import com.korosoft.tribalwarsremake.domain.event.dto.AttackEventDto;
import com.korosoft.tribalwarsremake.domain.player.Player;
import com.korosoft.tribalwarsremake.domain.player.repository.PlayerRepository;
import com.korosoft.tribalwarsremake.domain.village.Village;
import com.korosoft.tribalwarsremake.domain.village.VillageFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
class AttackEventFactory {

    private final ArmyFacade armyFacade;
    private final VillageFacade villageFacade;
    private final PlayerRepository playerRepository;

    AttackEvent createAttackEvent(AttackEventDto attackEventDto) {
        Village sourceVillage = this.villageFacade.getVillageReference(attackEventDto.getSourceVillageId());
        Village targetVillage = this.villageFacade.getVillageReference(attackEventDto.getTargetVillageId());
        List<Player> playerList = this.playerRepository.findAllById(attackEventDto.getPlayerIds());
        Army army = this.armyFacade.splitArmy(sourceVillage.getArmies().stream().filter(army1 -> army1.getSourceVillage() == sourceVillage).findFirst().get(), attackEventDto.getArmy());
        return new AttackEvent(attackEventDto.getEventStatus(), attackEventDto.getEventType(), attackEventDto.getStartTime(), attackEventDto.getEndTime(), playerList, targetVillage, sourceVillage, army);
    }
}
