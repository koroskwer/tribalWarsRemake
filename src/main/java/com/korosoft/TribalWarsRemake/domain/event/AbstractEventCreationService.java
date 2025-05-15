package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.event.dto.AbstractEventDto;

import java.time.Instant;

public interface AbstractEventCreationService<T extends AbstractEventDto> {
    void addEvent(T eventDto, Instant timestamp);
}
