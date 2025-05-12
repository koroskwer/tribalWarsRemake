package com.korosoft.TribalWarsRemake.domain.event.transport;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEventEntity;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@AllArgsConstructor
public class TransportEventFacade {

    private final TransportEventQueryService transportQueryService;

    public List<AbstractEventEntity> getTransportEvents(int playerId, Instant timestamp) {
        return this.transportQueryService.getTransportEvents(playerId, timestamp);
    }

    public void addTransportEvent(TransportEventDto transportEventDto, Instant timestamp) {
        this.transportQueryService.addTransportEvent(transportEventDto, timestamp);
    }
}
