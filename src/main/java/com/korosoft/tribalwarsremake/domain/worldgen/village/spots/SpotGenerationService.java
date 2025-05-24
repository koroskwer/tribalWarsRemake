package com.korosoft.tribalwarsremake.domain.worldgen.village.spots;

import com.korosoft.tribalwarsremake.domain.worldgen.WorldGenDirection;

import java.util.List;

public interface SpotGenerationService {

    List<Spot> generateNewSpots(WorldGenDirection direction);

}
