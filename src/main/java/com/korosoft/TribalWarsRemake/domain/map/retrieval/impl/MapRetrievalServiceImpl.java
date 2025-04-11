package com.korosoft.TribalWarsRemake.domain.map.retrieval.impl;

import com.korosoft.TribalWarsRemake.config.GameConstants;
import com.korosoft.TribalWarsRemake.domain.map.display.MapImageGenerationService;
import com.korosoft.TribalWarsRemake.domain.map.retrieval.MapRetrievalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
@AllArgsConstructor
public class MapRetrievalServiceImpl implements MapRetrievalService {

    private MapImageGenerationService mapImageGenerationService;

    @Override
    public byte[] getMapImage() throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ImageIO.write(this.mapImageGenerationService.generateMap(), GameConstants.MAP_IMAGE_FORMAT, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
}
