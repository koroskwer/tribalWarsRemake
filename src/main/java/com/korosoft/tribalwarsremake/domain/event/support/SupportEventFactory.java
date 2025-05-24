package com.korosoft.tribalwarsremake.domain.event.support;

import com.korosoft.tribalwarsremake.domain.army.Army;
import com.korosoft.tribalwarsremake.domain.army.ArmyFacade;
import com.korosoft.tribalwarsremake.domain.event.dto.SupportEventDto;
import com.korosoft.tribalwarsremake.domain.player.Player;
import com.korosoft.tribalwarsremake.domain.player.repository.PlayerRepository;
import com.korosoft.tribalwarsremake.domain.village.Village;
import com.korosoft.tribalwarsremake.domain.village.VillageFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
class SupportEventFactory {

    private final VillageFacade villageFacade;
    private final PlayerRepository playerRepository;
    private final ArmyFacade armyFacade;

    SupportEvent createSupportEvent(SupportEventDto supportEventDto) {
        Village sourceVillage = this.villageFacade.getVillageReference(supportEventDto.getSourceVillageId());
        Village targetVillage = this.villageFacade.getVillageReference(supportEventDto.getTargetVillageId());
        List<Player> playerList = this.playerRepository.findAllById(supportEventDto.getPlayerIds());
        Army army = this.armyFacade.splitArmy(sourceVillage.getArmies().stream().filter(army1 -> army1.getSourceVillage() == sourceVillage).findFirst().get(), supportEventDto.getArmy());

        return new SupportEvent(supportEventDto.getEventStatus(), supportEventDto.getEventType(), supportEventDto.getStartTime(), supportEventDto.getEndTime(), playerList, targetVillage, sourceVillage, army);
    }
}
