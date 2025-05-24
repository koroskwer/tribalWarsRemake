package com.korosoft.tribalwarsremake.rest;

import com.korosoft.tribalwarsremake.domain.map.retrieval.MapRetrievalService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/map")
@AllArgsConstructor
public class MapRestService {

    private MapRetrievalService mapRetrievalService;

    @GetMapping(
            value = "/fullMap",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<byte[]> getFullMap() throws IOException {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(this.mapRetrievalService.getMapImage());
    }
}
