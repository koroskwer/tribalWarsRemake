package com.korosoft.TribalWarsRemake.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController("/village")
public class VillageRestService {

    @GetMapping("/generate/single/{world}")
    public ResponseEntity<String> generateSingleAccount(){
        return new ResponseEntity<>("TODO", HttpStatus.OK);
    }

    @GetMapping("/generate/multiple/{world}")
    public ResponseEntity<String> generateMultipleAccount(@RequestParam int amount){
        return new ResponseEntity<>("TODO", HttpStatus.OK);
    }
}
