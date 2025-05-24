package com.korosoft.tribalwarsremake.domain.resources;

import com.korosoft.tribalwarsremake.domain.village.Village;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor
public class ResourcesFacade {

    private final ResourcesMerger resourcesMerger;
    private final ResourcesFactory resourcesFactory;
    private final ResourcesUpdater resourcesUpdater;
    private final ResourcesSplitter resourcesSplitter;

    public Resources mergeResources(Resources resources1, Resources resources2) {
        return this.resourcesMerger.merge(resources1, resources2);
    }

    public Resources createResources() {
        return this.resourcesFactory.createResources();
    }

    public Resources createResources(int wood, int clay, int iron) {
        return this.resourcesFactory.createResources(wood, clay, iron);
    }

    public void updateResources(Village village, Instant now) {
        this.resourcesUpdater.update(village, now);
    }


    /**
     * Substracts the individual resources from original Resources object by using data from resourcesDto
     * and returns the newly created resources object, which contains the data from dto.
     * @param resources Original resources object
     * @param resourcesDto Object holding data used for creation of new resource
     * @return Resource object created basing on resourcesDto
     */
    public Resources splitResources(Resources resources, ResourcesDto resourcesDto) {
        return this.resourcesSplitter.split(resources, resourcesDto);
    }
}
