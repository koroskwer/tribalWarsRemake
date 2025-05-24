package com.korosoft.tribalwarsremake.domain.battle;

import com.korosoft.tribalwarsremake.domain.army.Army;
import lombok.AllArgsConstructor;

import java.util.function.BinaryOperator;

@AllArgsConstructor
class ArmiesSumBiFunction implements BinaryOperator<Army> {

    @Override
    public Army apply(Army army, Army army2) {
        Army resultArmy = new Army();
        resultArmy.setPikemen(army.getPikemen() + army2.getPikemen());
        return resultArmy;
    }
}
