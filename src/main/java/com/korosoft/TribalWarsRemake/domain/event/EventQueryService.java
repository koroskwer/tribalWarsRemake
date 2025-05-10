package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.SupportEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;

import java.util.List;

interface EventQueryService {

    List<AbstractEvent> getAllEventsToProcess(int playerId);

    List<TransportEvent> getTransportEvents(int playerId);

    List<AttackEvent> getAttackEvents(int playerId);

    List<SupportEvent> getSupportEvents(int playerId);

    void addAttackEvent(AttackEventDto attackEventDto);

    void addSupportEvent(SupportEventDto supportEventDto);

    void addTransportEvent(TransportEventDto transportEventDto);
}
