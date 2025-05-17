package com.korosoft.TribalWarsRemake.domain.buildings;

import org.springframework.stereotype.Component;

@Component
public class BuildingsFacade {

    public ResourcePitsLevelsDto getResourcePitLevels(int villageId){
        //TODO get levels from Village.buildings object
        return new ResourcePitsLevelsDto(0,0,0);
    }

}
