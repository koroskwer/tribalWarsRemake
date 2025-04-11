package com.korosoft.TribalWarsRemake.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/continent")
public class ContinentRestService {

    @GetMapping("/dupa")
    public ResponseEntity<String> getCap(){
        return new ResponseEntity<>("siurson", HttpStatus.ACCEPTED);
    }

    @GetMapping("/map/{number}")
    public ResponseEntity<String> getContinentMap(){
        return new ResponseEntity<>("TODO", HttpStatus.OK);
    }
}
