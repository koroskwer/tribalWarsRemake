package com.korosoft.TribalWarsRemake.domain.worldgen.village.spots;

import com.korosoft.TribalWarsRemake.domain.worldgen.WorldGenDirection;

public interface SpotRetrievalService {
    Spot getRandomSpot(WorldGenDirection direction);
}
