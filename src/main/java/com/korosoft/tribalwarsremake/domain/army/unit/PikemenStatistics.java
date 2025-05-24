package com.korosoft.tribalwarsremake.domain.army.unit;

import com.korosoft.tribalwarsremake.domain.resources.ResourcesDto;
import org.springframework.stereotype.Component;

@Component
class PikemenStatistics implements UnitStatistics {

    private final static double PIKEMEN_SPEED = 30;
    private final static double PIKEMEN_ATTACK = 10;
    private final static double PIKEMEN_DEFENSE = 40;
    private final static int PIKEMEN_CARGO_CAPACITY = 20;
    private final static int PIKEMEN_FARM_COST = 1;
    private final static ResourcesDto PIKEMEN_COST_TO_BUILD = new ResourcesDto(50, 30, 10);

    @Override
    public double getSpeed() {
        return PIKEMEN_SPEED;
    }

    @Override
    public double getAttack() {
        return PIKEMEN_ATTACK;
    }

    @Override
    public double getDefense() {
        return PIKEMEN_DEFENSE;
    }

    @Override
    public int getCargoCapacity() {
        return PIKEMEN_CARGO_CAPACITY;
    }

    @Override
    public ResourcesDto getCostToBuild() {
        return PIKEMEN_COST_TO_BUILD;
    }

    @Override
    public int getFarmCost() {
        return PIKEMEN_FARM_COST;
    }
}
