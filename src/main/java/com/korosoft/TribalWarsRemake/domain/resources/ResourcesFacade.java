package com.korosoft.TribalWarsRemake.domain.resources;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ResourcesFacade {

    private final ResourcesMerger resourcesMerger;
    private final ResourcesFactory resourcesFactory;

    public Resources mergeResources(Resources resources1, Resources resources2) {
        return this.resourcesMerger.merge(resources1, resources2);
    }

    public Resources createResources() {
        return this.resourcesFactory.createResources();
    }

    public Resources createResources(int wood, int clay, int iron) {
        return this.resourcesFactory.createResources(wood, clay, iron);
    }
}
