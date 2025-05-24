package com.korosoft.tribalwarsremake.domain.army;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArmyFacade {

    private final ArmyRepository armyRepository;
    private final ArmySplitter armySplitter;
    private final ArmyMerger armyMerger;

    public Army getReferenceById(int id) {
        return this.armyRepository.getReferenceById(id);
    }

    /**
     * Creates a new army object which troops are subtracted from given army object
     * @param army Original army
     * @param armyDto Definition of new army object
     * @return New army object defined by the dto
     */
    public Army splitArmy(Army army, ArmyDto armyDto) {
        return this.armySplitter.split(army, armyDto);
    }

    /**
     * Merges second army object into the first one
     * @param army1 army to be merged into
     * @param army2 army to be merged from
     * @return army1 object with added army2 data
     */
    public Army mergeArmies(Army army1, Army army2) {
        return this.armyMerger.merge(army1, army2);
    }
}
