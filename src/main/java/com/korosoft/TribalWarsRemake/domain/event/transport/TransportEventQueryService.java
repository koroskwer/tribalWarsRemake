package com.korosoft.TribalWarsRemake.domain.event.transport;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEvent;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;

import java.time.Instant;
import java.util.List;

interface TransportEventQueryService {
    List<AbstractEvent> getTransportEvents(int playerId, Instant timestamp);

    void addTransportEvent(TransportEventDto transportEventDto, Instant timestamp);

    void removeTransportEvents(int playerId, Instant timestamp);
}
