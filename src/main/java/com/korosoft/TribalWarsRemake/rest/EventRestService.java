package com.korosoft.TribalWarsRemake.rest;

import com.korosoft.TribalWarsRemake.domain.event.EventServiceFacade;
import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.SupportEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventRestService {

    private final EventServiceFacade eventServiceFacade;

    @PostMapping("/process/{playerId}")
    public ResponseEntity<String> processEvents(@PathVariable int playerId) {
        this.eventServiceFacade.processEvents(playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

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
