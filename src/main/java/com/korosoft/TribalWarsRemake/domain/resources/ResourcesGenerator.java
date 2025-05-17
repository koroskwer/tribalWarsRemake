package com.korosoft.TribalWarsRemake.domain.resources;

import com.korosoft.TribalWarsRemake.config.ResourceGenerationConstants;
import com.korosoft.TribalWarsRemake.domain.buildings.BuildingsFacade;
import com.korosoft.TribalWarsRemake.domain.buildings.ResourcePitsLevelsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class ResourcesGenerator {

    private final ResourcesFactory resourcesFactory;
    private final ResourcesMerger resourcesMerger;
    private final BuildingsFacade buildingsFacade;

    Resources generateResources(Resources currentResources, long passedSeconds, int villageId) {
        ResourcePitsLevelsDto resourcePitsLevelsDto = this.buildingsFacade.getResourcePitLevels(villageId);
        Resources resourcesToAdd = this.resourcesFactory.createResources(
                this.generateResource(resourcePitsLevelsDto.woodPit(), passedSeconds),
                this.generateResource(resourcePitsLevelsDto.clayPit(), passedSeconds),
                this.generateResource(resourcePitsLevelsDto.ironPit(), passedSeconds));
        return this.resourcesMerger.merge(resourcesToAdd, currentResources);
    }

    private int generateResource(int buildingLevel, long passedSeconds){
        return Math.toIntExact(ResourceGenerationConstants.DEFAULT_RESOURCE_GENERATION_PER_BUILDING_LEVEL[buildingLevel] * (passedSeconds / 3600));
    }

}
