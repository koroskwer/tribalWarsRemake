package com.korosoft.tribalwarsremake.domain.worldgen.village.spots;

import com.korosoft.tribalwarsremake.domain.worldgen.WorldGenDirection;

import java.util.List;

public interface SpotRetrievalService {
    Spot getRandomSpot(WorldGenDirection direction);

    List<Spot> getRandomSpots(WorldGenDirection direction, int amount);
}
