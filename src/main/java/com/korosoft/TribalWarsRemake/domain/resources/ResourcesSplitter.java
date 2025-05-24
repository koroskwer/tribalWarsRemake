package com.korosoft.TribalWarsRemake.domain.resources;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class ResourcesSplitter {

    private final ResourcesRepository resourcesRepository;
    private final ResourcesFactory resourcesFactory;
    private final ResourcesValidator resourcesValidator;

    Resources split(Resources resources, ResourcesDto resourcesDto) {
        if(!this.resourcesValidator.areResourcesValid(resourcesDto)){
            throw new IllegalArgumentException("ResourcesDto is not valid");
        }
        ResourcesDto subtractedResourcesDto = this.subtract(resources, resourcesDto);
        if(!this.resourcesValidator.areResourcesValid(subtractedResourcesDto) ){
            throw new IllegalArgumentException("Resources are not valid");
        }
        Resources toSubtractResources = this.resourcesFactory.createResources(resourcesDto);
        this.updateResources(resources, subtractedResourcesDto);
        this.resourcesRepository.save(resources);
        this.resourcesRepository.save(toSubtractResources);

        return toSubtractResources;
    }

    private ResourcesDto subtract(Resources resources, ResourcesDto resourcesDto) {
        return new ResourcesDto(resources.getWood() - resourcesDto.wood(), resources.getClay() - resourcesDto.clay(), resources.getIron() - resourcesDto.iron());
    }

    private void updateResources(Resources resources, ResourcesDto resourcesDto) {
        resources.setWood(resourcesDto.wood());
        resources.setClay(resourcesDto.clay());
        resources.setIron(resourcesDto.iron());
    }
}
