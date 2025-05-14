package com.korosoft.TribalWarsRemake.domain.event.attack;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEvent;
import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;

import java.time.Instant;
import java.util.List;

public interface AttackEventQueryService {
    List<AbstractEvent> getAttackEvents(int playerId, Instant timestamp);

    void addAttackEvent(AttackEventDto AttackEventDto, Instant timestamp);
}
