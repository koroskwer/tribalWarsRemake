package com.korosoft.tribalwarsremake.domain.worldgen.village.spots.impl;

import com.korosoft.tribalwarsremake.domain.worldgen.WorldGenDirection;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.Spot;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.SpotGenerationService;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.SpotGenerator;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.generationlevel.SpotGenerationLevel;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.generationlevel.SpotGenerationLevelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SpotGenerationServiceImpl implements SpotGenerationService {

    private final SpotGenerationLevelRepository spotGenerationLevelRepository;
    private final SpotGenerator spotGenerator;

    @Override
    public List<Spot> generateNewSpots(WorldGenDirection direction) {
        SpotGenerationLevel spotGenerationLevel = this.spotGenerationLevelRepository.findByDirection(direction.getKey());
        int generationLevel = spotGenerationLevel.getGenerationLevel();
        List<Spot> spots = this.spotGenerator.generateSpots(generationLevel + 1, direction);
        spotGenerationLevel.increaseSpotGenerationLevel(spots.size());
        this.spotGenerationLevelRepository.save(spotGenerationLevel);
        return spots;
    }
}
