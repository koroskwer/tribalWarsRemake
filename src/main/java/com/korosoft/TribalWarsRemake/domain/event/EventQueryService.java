package com.korosoft.TribalWarsRemake.domain.event;

import java.util.List;

interface EventQueryService {

    List<AbstractEvent> getAllEventsToProcess(int playerId);

    List<TransportEvent> getTransportEvents(int playerId);

    List<AttackEvent> getAttackEvents(int playerId);

    List<SupportEvent> getSupportEvents(int playerId);
}
