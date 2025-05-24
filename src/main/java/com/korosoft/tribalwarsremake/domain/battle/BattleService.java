package com.korosoft.tribalwarsremake.domain.battle;

import com.korosoft.tribalwarsremake.domain.army.Army;
import com.korosoft.tribalwarsremake.domain.army.ArmyFacade;
import com.korosoft.tribalwarsremake.domain.army.unit.UnitFacade;
import com.korosoft.tribalwarsremake.domain.army.unit.UnitStatistics;
import com.korosoft.tribalwarsremake.domain.village.Village;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;

@Component
@AllArgsConstructor
/*
TODO get unit strength once and save it in memory
TODO create reports system
 */
class BattleService {

    private final UnitFacade unitFacade;
    //TODO Consider giving access to armyRepository directly
    private final ArmyFacade armyFacade;

    void runBattle(Village defendingVillage, Army attackingArmy) {
        Army defendingArmy = defendingVillage.getArmies().stream().reduce(new Army(), this.combineArmies());
        //TODO get city walls levels, morale, luck, and bonuses
        double armyPowerProportions = this.getDefensePowerOfArmy(defendingArmy) / this.getAttackPowerOfArmy(attackingArmy);
        if (armyPowerProportions < 1) {
            // Attacker won
            this.armyFacade.deleteArmies(defendingVillage.getArmies());
            this.applyLosses(armyPowerProportions).accept(attackingArmy);
            this.armyFacade.saveArmy(attackingArmy);
        } else {
            // Defender won
            this.armyFacade.deleteArmy(attackingArmy);
            defendingVillage.getArmies().forEach(this.applyLosses(armyPowerProportions));
            this.armyFacade.saveArmies(defendingVillage.getArmies());
        }
    }

    private double getDefensePowerOfArmy(Army army) {
        //TODO repeat for every added unit... how can i make it more flexible?
        int pikemen = army.getPikemen();
        UnitStatistics pikemenStatistics = this.unitFacade.getPikemenStatistics();
        return pikemen * pikemenStatistics.getDefense();
    }

    private double getAttackPowerOfArmy(Army army) {
        //TODO repeat for every added unit... how can i make it more flexible?
        int pikemen = army.getPikemen();
        UnitStatistics pikemenStatistics = this.unitFacade.getPikemenStatistics();
        return pikemen * pikemenStatistics.getAttack();
    }

    private Consumer<Army> applyLosses(double proportion) {
        return (army -> {
            int pikemen = army.getPikemen();
            army.setPikemen((int) (pikemen - (pikemen / proportion)));
        });
    }

    private BinaryOperator<Army> combineArmies(){
        return ((army1, army2) -> {
            Army resultArmy = new Army();
            resultArmy.setPikemen(army1.getPikemen() + army2.getPikemen());
            return resultArmy;
        });
    }
}
