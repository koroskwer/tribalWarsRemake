package com.korosoft.tribalwarsremake.domain.worldgen.village.spots;

import com.korosoft.tribalwarsremake.config.GameConstants;
import com.korosoft.tribalwarsremake.domain.worldgen.WorldGenDirection;

public class SpotFlipper {

    public Spot createTransformedSpot(int x, int y, WorldGenDirection direction) {
        return switch (direction) {
            case NORTH_EAST -> this.createNorthEastSpot(x, y);
            case NORTH_WEST -> this.createNorthWestSpot(x, y);
            case SOUTH_EAST -> this.createSouthEastSpot(x, y);
            case SOUTH_WEST -> this.createSouthWestSpot(x, y);
        };
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
