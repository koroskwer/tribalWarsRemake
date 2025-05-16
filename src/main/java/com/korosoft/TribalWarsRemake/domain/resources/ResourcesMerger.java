package com.korosoft.TribalWarsRemake.domain.resources;


import org.springframework.stereotype.Component;

@Component
class ResourcesMerger {

    Resources merge(Resources r1, Resources r2) {
        return new Resources(r1.getWood() + r2.getWood(), r1.getClay()+r2.getClay(), r1.getIron()+r2.getIron());
    }
}
