package com.korosoft.tribalwarsremake.domain.army;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class ArmySplitter {

    private final ArmyRepository armyRepository;

    Army split(Army army, ArmyDto armyDto) {
        int pikemenAmount = army.getPikemen() - armyDto.pikemen();
        if (pikemenAmount < 0) {
            throw new IllegalArgumentException("pikemen must be greater than zero");
        }
        army.setPikemen(pikemenAmount);
        this.armyRepository.save(army);
        Army createdArmy = new Army(armyDto.pikemen(), army.getSourceVillage());
        createdArmy = this.armyRepository.save(createdArmy);
        return createdArmy;
    }
}
