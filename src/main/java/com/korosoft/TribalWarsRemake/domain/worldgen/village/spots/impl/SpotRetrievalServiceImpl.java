package com.korosoft.TribalWarsRemake.domain.worldgen.village.spots.impl;

import com.korosoft.TribalWarsRemake.domain.worldgen.WorldGenDirection;
import com.korosoft.TribalWarsRemake.domain.worldgen.village.spots.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SpotRetrievalServiceImpl implements SpotRetrievalService {

    private static final SecureRandom random = new SecureRandom();
    private final SpotQueryService spotQueryService;
    private final SpotGenerationService spotGenerationService;
    private final SpotRepository spotRepository;


    private Map<WorldGenDirection, Integer> cacheCheck = new HashMap<>();

    {
        this.cacheCheck.put(WorldGenDirection.NORTH_EAST, 0);
        this.cacheCheck.put(WorldGenDirection.SOUTH_EAST, 0);
        this.cacheCheck.put(WorldGenDirection.SOUTH_WEST, 0);
        this.cacheCheck.put(WorldGenDirection.NORTH_WEST, 0);
    }

    @Override
    public Spot getRandomSpot(WorldGenDirection direction) {
        boolean areEnoughSpotsLeft = this.checkIfThereAreEnoughSpotsLeft(direction, 1);

        if (areEnoughSpotsLeft) {
            return this.spotQueryService.getRandomSpots(direction, 1).get(0);
        }
        this.spotQueryService.deleteRedundantSpots(direction);
        List<Spot> newSpots = this.spotGenerationService.generateNewSpots(direction);

        Spot spot = newSpots.get(random.nextInt(newSpots.size()));
        newSpots.remove(spot);
        this.spotRepository.saveAll(newSpots);

        return spot;
    }

    @Override
    public List<Spot> getRandomSpots(WorldGenDirection direction, int amount) {
        boolean areEnoughSpotsLeft = this.checkIfThereAreEnoughSpotsLeft(direction, amount);

        if (areEnoughSpotsLeft) {
            return this.spotQueryService.getRandomSpots(direction, amount);
        }
        this.spotQueryService.deleteRedundantSpots(direction);
        List<Spot> newSpots = this.spotGenerationService.generateNewSpots(direction);

        List<Spot> returnedSpots = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            Spot spot = newSpots.get(random.nextInt(newSpots.size()));
            newSpots.remove(spot);
            returnedSpots.add(spot);
        }
        this.spotRepository.saveAll(newSpots);

        return returnedSpots;
    }

    private boolean checkIfThereAreEnoughSpotsLeft(WorldGenDirection direction, int amount) {
        Boolean areEnoughSpotsLeft = true;
        if (cacheCheck.get(direction) == 0) {
            areEnoughSpotsLeft = this.spotQueryService.areEnoughSpotsLeft(direction, amount);
            cacheCheck.put(direction, 10);
        }
        cacheCheck.put(direction, cacheCheck.get(direction) - 1);
        return areEnoughSpotsLeft;
    }
}
