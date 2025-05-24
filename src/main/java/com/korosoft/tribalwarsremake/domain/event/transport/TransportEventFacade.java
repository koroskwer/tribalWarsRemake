package com.korosoft.tribalwarsremake.domain.event.transport;

import com.korosoft.tribalwarsremake.domain.event.AbstractEvent;
import com.korosoft.tribalwarsremake.domain.event.dto.TransportEventDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TransportEventFacade {

    private final TransportEventRetrievalService transportEventRetrievalService;
    private final TransportEventCreationService transportEventCreationService;

    public List<AbstractEvent> getTransportEvents(int playerId, Instant timestamp) {
        return new ArrayList<>(this.transportEventRetrievalService.getEvents(playerId, timestamp));
    }

    public void addTransportEvent(TransportEventDto transportEventDto, Instant timestamp) {
        this.transportEventCreationService.addEvent(transportEventDto, timestamp);
    }
}
