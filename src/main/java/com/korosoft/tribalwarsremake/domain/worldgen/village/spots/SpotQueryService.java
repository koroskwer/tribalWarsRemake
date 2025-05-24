package com.korosoft.tribalwarsremake.domain.worldgen.village.spots;


import com.korosoft.tribalwarsremake.domain.worldgen.WorldGenDirection;

import java.util.Collection;
import java.util.List;

public interface SpotQueryService {
    Collection<Spot> getAllSpots(WorldGenDirection direction);

    Long getCountOfSpots(WorldGenDirection direction);

    void deleteRedundantSpots(WorldGenDirection direction);

    Long getMinimumSpots(WorldGenDirection direction);

    Boolean areEnoughSpotsLeft(WorldGenDirection direction, int amount);

    List<Spot> getRandomSpots(WorldGenDirection direction, int amount);
}
