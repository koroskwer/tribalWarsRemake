package com.korosoft.TribalWarsRemake.domain.event.attack;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEvent;
import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@AllArgsConstructor
public class AttackEventFacade {

    private final AttackEventQueryService attackQueryService;

    public List<AbstractEvent> getAttackEvents(int playerId, Instant timestamp) {
        return this.attackQueryService.getAttackEvents(playerId, timestamp);
    }

    public void addAttackEvent(AttackEventDto attackEventDto, Instant timestamp) {
        this.attackQueryService.addAttackEvent(attackEventDto, timestamp);
    }
}
