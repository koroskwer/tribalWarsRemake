package com.korosoft.TribalWarsRemake.domain.resources;

import com.korosoft.TribalWarsRemake.domain.village.Village;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
@AllArgsConstructor
public class ResourcesFacade {

    private final ResourcesMerger resourcesMerger;
    private final ResourcesFactory resourcesFactory;
    private final ResourcesUpdater resourcesUpdater;

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
}
