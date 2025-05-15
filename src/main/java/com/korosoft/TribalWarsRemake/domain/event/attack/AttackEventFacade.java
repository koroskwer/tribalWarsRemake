package com.korosoft.TribalWarsRemake.domain.event.attack;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEvent;
import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class AttackEventFacade {

    private final AttackEventCreationService attackEventCreationService;
    private final AttackEventRetrievalService attackEventRetrievalService;

    public List<AbstractEvent> getAttackEvents(int playerId, Instant timestamp) {
        return new ArrayList<>(this.attackEventRetrievalService.getEvents(playerId, timestamp));
    }

    public void addAttackEvent(AttackEventDto attackEventDto, Instant timestamp) {
        this.attackEventCreationService.addEvent(attackEventDto, timestamp);
    }
}
