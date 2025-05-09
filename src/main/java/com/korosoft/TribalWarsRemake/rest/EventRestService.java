package com.korosoft.TribalWarsRemake.rest;

import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.SupportEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventRestService {
    @PostMapping("/attack")
    public ResponseEntity<String> generateAttackEvent(@RequestBody AttackEventDto attackEventDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/transport")
    public ResponseEntity<String> generateTransportEvent(@RequestBody TransportEventDto transportEventDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/support")
    public ResponseEntity<String> generateSupportEvent(@RequestBody SupportEventDto supportEventDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
