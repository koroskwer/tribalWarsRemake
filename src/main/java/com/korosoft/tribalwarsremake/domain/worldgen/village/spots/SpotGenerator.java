package com.korosoft.tribalwarsremake.domain.worldgen.village.spots;

import com.korosoft.tribalwarsremake.domain.worldgen.WorldGenDirection;

import java.util.List;

public interface SpotGenerator {
    List<Spot> generateSpots(int generationLevel, WorldGenDirection direction);
}
