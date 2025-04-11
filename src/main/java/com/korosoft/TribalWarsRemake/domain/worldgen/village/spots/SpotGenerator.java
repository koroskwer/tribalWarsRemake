package com.korosoft.TribalWarsRemake.domain.worldgen.village.spots;

import com.korosoft.TribalWarsRemake.domain.worldgen.WorldGenDirection;

import java.util.List;

public interface SpotGenerator {
    List<Spot> generateSpots(int generationLevel, WorldGenDirection direction);
}
