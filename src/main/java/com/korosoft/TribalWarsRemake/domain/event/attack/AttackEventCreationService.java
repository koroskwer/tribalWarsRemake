package com.korosoft.TribalWarsRemake.domain.event.attack;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEventCreationService;
import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor
class AttackEventCreationService implements AbstractEventCreationService<AttackEventDto> {

    private final AttackEventRepository attackEventRepository;
    private final AttackEventFactory attackEventFactory;

    @Override
    public void addEvent(AttackEventDto eventDto, Instant timestamp) {
        this.attackEventRepository.save(this.attackEventFactory.createAttackEvent());
    }
}
