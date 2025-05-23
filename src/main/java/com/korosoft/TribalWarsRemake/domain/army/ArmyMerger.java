package com.korosoft.TribalWarsRemake.domain.army;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class ArmyMerger {

    private ArmyRepository armyRepository;

    Army merge(Army army1, Army army2) {
        this.sumUnits(army1, army2);
        this.armyRepository.delete(army2);
        army1 = this.armyRepository.save(army1);
        return army1;
    }

    private void sumUnits(Army army1, Army army2) {
        army1.setPikemen(army1.getPikemen() + army2.getPikemen());
    }

}
