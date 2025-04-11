package com.korosoft.TribalWarsRemake.domain.worldgen.village.spots.impl;

import com.korosoft.TribalWarsRemake.config.GameConstants;
import com.korosoft.TribalWarsRemake.domain.worldgen.WorldGenDirection;
import com.korosoft.TribalWarsRemake.domain.worldgen.village.spots.Spot;
import com.korosoft.TribalWarsRemake.domain.worldgen.village.spots.SpotGenerator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SlowSpotGeneratorImpl implements SpotGenerator {
    @Override
    public List<Spot> generateSpots(int generationLevel, WorldGenDirection direction) {
        int xStart = 0;
        int xEnd = generationLevel * GameConstants.MAP_GENERATION_STEP_SIZE + 20;

        int yStart = 0;
        int yEnd = generationLevel * GameConstants.MAP_GENERATION_STEP_SIZE + 20;

        int radiusStart = 0;
        int radiusEnd = 20;

        if (generationLevel > 0) {
            radiusStart = generationLevel * GameConstants.MAP_GENERATION_STEP_SIZE;
            radiusEnd = (generationLevel + 1) * GameConstants.MAP_GENERATION_STEP_SIZE - 1;
        }

        List<Spot> spots = new ArrayList<>();

        for (int x = xStart; x <= xEnd; x++) {
            for (int y = yStart; y <= yEnd; y++) {
                if (isPointInsideDonut(x, y, radiusStart, radiusEnd)) {
                    spots.add(this.createTransformedSpot(x, y, direction));
                }
            }
        }
        return spots;
    }


    private Spot createTransformedSpot(int x, int y, WorldGenDirection direction) {
        return switch (direction) {
            case NORTH_EAST -> this.createNorthEastSpot(x, y);
            case NORTH_WEST -> this.createNorthWestSpot(x, y);
            case SOUTH_EAST -> this.createSouthEastSpot(x, y);
            case SOUTH_WEST -> this.createSouthWestSpot(x, y);
        };
    }

    private boolean isPointInsideDonut(int x, int y, int radiusStart, int radiusEnd) {
        int coordsResult = x * x + y * y;
        return (coordsResult >= radiusStart * radiusStart && coordsResult < radiusEnd * radiusEnd);
    }

    private Spot createNorthEastSpot(int x, int y) {
        return new Spot(x + GameConstants.HALF_MAP_SIZE, GameConstants.HALF_MAP_SIZE - y - 1, WorldGenDirection.NORTH_EAST);
    }

    private Spot createNorthWestSpot(int x, int y) {
        return new Spot(GameConstants.HALF_MAP_SIZE - x - 1, GameConstants.HALF_MAP_SIZE - y - 1, WorldGenDirection.NORTH_WEST);
    }

    private Spot createSouthEastSpot(int x, int y) {
        return new Spot(x + GameConstants.HALF_MAP_SIZE, GameConstants.HALF_MAP_SIZE + y, WorldGenDirection.SOUTH_EAST);
    }

    private Spot createSouthWestSpot(int x, int y) {
        return new Spot(GameConstants.HALF_MAP_SIZE - x - 1, GameConstants.HALF_MAP_SIZE + y, WorldGenDirection.SOUTH_WEST);
    }
}