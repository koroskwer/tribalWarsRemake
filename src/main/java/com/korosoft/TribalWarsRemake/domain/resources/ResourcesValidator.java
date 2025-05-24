package com.korosoft.TribalWarsRemake.domain.resources;

import org.springframework.stereotype.Component;

@Component
class ResourcesValidator {

    boolean areResourcesValid(Resources resources) {
        if(resources == null)
            return false;
        if(resources.getClay() < 0)
            return false;
        if (resources.getWood() < 0)
            return false;
        if (resources.getIron() < 0)
            return false;
        return true;
    }

    boolean areResourcesValid(ResourcesDto resourcesDto) {
        if(resourcesDto == null)
            return false;
        if(resourcesDto.clay() < 0)
            return false;
        if (resourcesDto.wood() < 0)
            return false;
        if (resourcesDto.iron() < 0)
            return false;
        return true;
    }

}
