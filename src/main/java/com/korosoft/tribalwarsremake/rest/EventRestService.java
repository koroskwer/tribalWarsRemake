package com.korosoft.tribalwarsremake.rest;

import com.korosoft.tribalwarsremake.domain.event.EventServiceFacade;
import com.korosoft.tribalwarsremake.domain.event.dto.AttackEventDto;
import com.korosoft.tribalwarsremake.domain.event.dto.SupportEventDto;
import com.korosoft.tribalwarsremake.domain.event.dto.TransportEventDto;
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
        this.eventServiceFacade.createAttackEvent(attackEventDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/transport")
    public ResponseEntity<String> generateTransportEvent(@RequestBody TransportEventDto transportEventDto) {
        this.eventServiceFacade.createTransportEvent(transportEventDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/support")
    public ResponseEntity<String> generateSupportEvent(@RequestBody SupportEventDto supportEventDto) {
        this.eventServiceFacade.createSupportEvent(supportEventDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
