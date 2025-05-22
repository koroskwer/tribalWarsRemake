package com.korosoft.TribalWarsRemake.domain.resources;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class ResourcesFactory {

    public Resources createResources() {
        return new Resources(0, 0, 0);
    }

    public Resources createResources(int wood, int clay, int iron) {
        return new Resources(iron, clay, wood);
    }
}
