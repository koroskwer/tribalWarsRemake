package com.korosoft.TribalWarsRemake.domain.resources;

import com.korosoft.TribalWarsRemake.domain.village.Village;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@AllArgsConstructor
class ResourcesUpdater {

    private final ResourcesFacade resourcesFacade;
    private final ResourcesGenerator resourcesGenerator;

    void update(Village village, Instant now) {
        Duration duration = Duration.between(now, village.getLastResourceGeneration());
        long secondsSinceLastUpdate = duration.getSeconds();
        village.setResources(this.resourcesGenerator.generateResources(village.getResources(), secondsSinceLastUpdate, village.getId()));
    }
}
