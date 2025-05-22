package com.korosoft.TribalWarsRemake.domain.resources;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class ResourcesFactory {

    private final ResourcesRepository resourcesRepository;

    public Resources createResources() {
        return this.resourcesRepository.save(new Resources(0, 0, 0));
    }

    public Resources createResources(int wood, int clay, int iron) {
        return this.resourcesRepository.save(new Resources(iron, clay, wood));
    }
}
