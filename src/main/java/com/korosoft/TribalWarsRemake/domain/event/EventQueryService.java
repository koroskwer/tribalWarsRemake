package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.SupportEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;

import java.time.Instant;
import java.util.List;

interface EventQueryService {

    List<AbstractEvent> getAllEventsToProcess(int playerId, Instant timestamp);

    List<AttackEvent> getAttackEvents(int playerId, Instant timestamp);

    List<SupportEvent> getSupportEvents(int playerId, Instant timestamp);

    void addAttackEvent(AttackEventDto attackEventDto, Instant timestamp);

    void addSupportEvent(SupportEventDto supportEventDto, Instant timestamp);

    void addTransportEvent(TransportEventDto transportEventDto, Instant timestamp);
}
