package com.korosoft.TribalWarsRemake.domain.worldgen.village.spots;


import com.korosoft.TribalWarsRemake.domain.worldgen.WorldGenDirection;

import java.util.Collection;

public interface SpotQueryService {
    Collection<Spot> getAllSpots(WorldGenDirection direction);

    Long getCountOfSpots(WorldGenDirection direction);

    void deleteRedundantSpots(WorldGenDirection direction);

    Long getMinimumSpots(WorldGenDirection direction);

    Boolean areEnoughSpotsLeft(WorldGenDirection direction);

    Spot getRandomSpot(WorldGenDirection direction);
}
