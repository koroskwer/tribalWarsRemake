package com.korosoft.tribalwarsremake.domain.army.unit;

import com.korosoft.tribalwarsremake.domain.resources.ResourcesDto;

public interface UnitStatistics {
    double getSpeed();
    double getAttack();
    double getDefense();
    int getCargoCapacity();
    ResourcesDto getCostToBuild();
    int getFarmCost();
}
