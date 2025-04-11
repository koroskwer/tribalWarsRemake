package com.korosoft.TribalWarsRemake.domain.worldgen.village.spots;

import com.korosoft.TribalWarsRemake.domain.worldgen.WorldGenDirection;

import java.util.List;

public interface SpotRetrievalService {
    Spot getRandomSpot(WorldGenDirection direction);

    List<Spot> getRandomSpots(WorldGenDirection direction, int amount);
}
