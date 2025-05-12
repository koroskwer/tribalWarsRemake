package com.korosoft.TribalWarsRemake.domain.event.transport;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEventEntity;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;

import java.time.Instant;
import java.util.List;

interface TransportEventQueryService {
    List<AbstractEventEntity> getTransportEvents(int playerId, Instant timestamp);

    void addTransportEvent(TransportEventDto transportEventDto, Instant timestamp);

    void removeTransportEvents(int playerId, Instant timestamp);
}
