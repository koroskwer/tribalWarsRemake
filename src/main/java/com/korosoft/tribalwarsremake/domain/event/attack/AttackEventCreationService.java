package com.korosoft.tribalwarsremake.domain.event.attack;

import com.korosoft.tribalwarsremake.domain.event.AbstractEventCreationService;
import com.korosoft.tribalwarsremake.domain.event.dto.AttackEventDto;
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
        this.attackEventRepository.save(this.attackEventFactory.createAttackEvent(eventDto));
    }
}
