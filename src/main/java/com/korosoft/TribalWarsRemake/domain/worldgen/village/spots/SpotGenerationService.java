package com.korosoft.TribalWarsRemake.domain.worldgen.village.spots;

import com.korosoft.TribalWarsRemake.domain.worldgen.WorldGenDirection;

import java.util.List;

public interface SpotGenerationService {

    List<Spot> generateNewSpots(WorldGenDirection direction);

}
