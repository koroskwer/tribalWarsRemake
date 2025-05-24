package com.korosoft.tribalwarsremake.domain.map.display.impl;

import com.korosoft.tribalwarsremake.config.GameConstants;
import com.korosoft.tribalwarsremake.domain.map.display.MapImageGenerationService;
import com.korosoft.tribalwarsremake.domain.village.Village;
import com.korosoft.tribalwarsremake.domain.village.repository.VillageRepository;
import com.korosoft.tribalwarsremake.domain.worldgen.WorldGenDirection;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.Spot;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.SpotQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;

@Component
@AllArgsConstructor
public class MapImageGenerationServiceImpl implements MapImageGenerationService {

    private final SpotQueryService spotQueryService;
    private final VillageRepository villageRepository;
    private final int spotColorRGB = new Color(50, 50, 200).getRGB();
    private final int villageColorRGB = new Color(200, 50, 50).getRGB();

    @Override
    public BufferedImage generateMap() {
        BufferedImage map = new BufferedImage(GameConstants.MAP_SIZE, GameConstants.MAP_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D mapBackground = map.createGraphics();
        mapBackground.setColor(new Color(50, 150, 50));
        mapBackground.fillRect(0, 0, GameConstants.MAP_SIZE, GameConstants.MAP_SIZE);

        Collection<Village> villages = villageRepository.findAll();
        for (Village v : villages) {
            map.setRGB(v.getXCoord(), v.getYCoord(), villageColorRGB);
        }

        Collection<Spot> spots = spotQueryService.getAllSpots(WorldGenDirection.SOUTH_EAST);
        for (Spot s : spots) {
            map.setRGB(s.getXCoord(), s.getYCoord(), spotColorRGB);
        }
        spots = this.spotQueryService.getAllSpots(WorldGenDirection.SOUTH_WEST);
        for (Spot s : spots) {
            map.setRGB(s.getXCoord(), s.getYCoord(), spotColorRGB);
        }
        spots = this.spotQueryService.getAllSpots(WorldGenDirection.NORTH_EAST);
        for (Spot s : spots) {
            map.setRGB(s.getXCoord(), s.getYCoord(), spotColorRGB);
        }

        spots = this.spotQueryService.getAllSpots(WorldGenDirection.NORTH_WEST);
        for (Spot s : spots) {
            map.setRGB(s.getXCoord(), s.getYCoord(), spotColorRGB);
        }


        return map;
    }
}
