package com.korosoft.TribalWarsRemake.domain.worldgen.village.spots.impl;

import com.korosoft.TribalWarsRemake.domain.worldgen.WorldGenDirection;
import com.korosoft.TribalWarsRemake.domain.worldgen.village.spots.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SpotRetrievalServiceImpl implements SpotRetrievalService {

    private static final SecureRandom random = new SecureRandom();
    private final SpotQueryService spotQueryService;
    private final SpotGenerationService spotGenerationService;
    private final SpotRepository spotRepository;


    private int cacheCheck = 0;

    @Override
    public Spot getRandomSpot(WorldGenDirection direction) {
        Boolean areEnoughSpotsLeft = true;
        if (cacheCheck == 0) {
            areEnoughSpotsLeft = this.spotQueryService.areEnoughSpotsLeft(direction);
            cacheCheck = 50;
        }
        cacheCheck--;

        if (areEnoughSpotsLeft != null && areEnoughSpotsLeft) {
            return this.spotQueryService.getRandomSpot(direction);
        }
        this.spotQueryService.deleteRedundantSpots(direction);
        List<Spot> newSpots = this.spotGenerationService.generateNewSpots(direction);
        Spot spot = newSpots.get(random.nextInt(newSpots.size()));
        newSpots.remove(spot);
        this.spotRepository.saveAll(newSpots);

        return spot;
    }
}
