package com.korosoft.tribalwarsremake.domain.resources;

import com.korosoft.tribalwarsremake.domain.village.Village;
import com.korosoft.tribalwarsremake.domain.village.repository.VillageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@AllArgsConstructor
class ResourcesUpdater {

    private final ResourcesGenerator resourcesGenerator;
    private final VillageRepository villageRepository;

    void update(Village village, Instant now) {
        Duration duration = Duration.between(village.getLastResourceGeneration(), now);
        long secondsSinceLastUpdate = duration.getSeconds();
        village.setResources(this.resourcesGenerator.generateResources(village.getResources(), secondsSinceLastUpdate, village.getId()));
        village.setLastResourceGeneration(now);
        this.villageRepository.save(village);
    }
}
