package com.korosoft.tribalwarsremake.domain.event;

import com.korosoft.tribalwarsremake.domain.event.dto.AbstractEventDto;

import java.time.Instant;

public interface AbstractEventCreationService<T extends AbstractEventDto> {
    void addEvent(T eventDto, Instant timestamp);
}
