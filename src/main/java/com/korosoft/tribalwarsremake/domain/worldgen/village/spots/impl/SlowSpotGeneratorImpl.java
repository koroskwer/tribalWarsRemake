package com.korosoft.tribalwarsremake.domain.worldgen.village.spots.impl;

import com.korosoft.tribalwarsremake.config.GameConstants;
import com.korosoft.tribalwarsremake.domain.worldgen.WorldGenDirection;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.Spot;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.SpotFlipper;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.SpotGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Checks the entire Quarter of a square when searching for valid points inside the donut
 */
@Component
@AllArgsConstructor
public class SlowSpotGeneratorImpl implements SpotGenerator {

    private final SpotFlipper flipper = new SpotFlipper();

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
                    spots.add(this.flipper.createTransformedSpot(x, y, direction));
                }
            }
        }
        return spots;
    }

    private boolean isPointInsideDonut(int x, int y, int radiusStart, int radiusEnd) {
        int coordsResult = x * x + y * y;
        return (coordsResult >= radiusStart * radiusStart && coordsResult < radiusEnd * radiusEnd);
    }
}