package com.korosoft.TribalWarsRemake.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/map")
public class MapRestService {

    @GetMapping("/fullMap/{world}")
    public ResponseEntity<String> getFullMap(){
        return new ResponseEntity<>("TODO", HttpStatus.OK);
    }

    @GetMapping("/generate/fullMap/{world}")
    public ResponseEntity<String> generateFullMap(){
        return new ResponseEntity<>("TODO", HttpStatus.OK);
    }
}
