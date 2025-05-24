package com.korosoft.tribalwarsremake.domain.event.support;

import com.korosoft.tribalwarsremake.domain.event.AbstractEventCreationService;
import com.korosoft.tribalwarsremake.domain.event.dto.SupportEventDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor
class SupportEventCreationService implements AbstractEventCreationService<SupportEventDto> {

    private final SupportEventRepository supportEventRepository;
    private final SupportEventFactory supportEventFactory;

    @Override
    public void addEvent(SupportEventDto eventDto, Instant timestamp) {
        this.supportEventRepository.save(this.supportEventFactory.createSupportEvent(eventDto));
    }
}
