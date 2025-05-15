package com.korosoft.TribalWarsRemake.domain.event.dto;

import com.korosoft.TribalWarsRemake.domain.event.EventStatus;
import com.korosoft.TribalWarsRemake.domain.event.EventType;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
public abstract class AbstractEventDto {
    protected final EventStatus eventStatus;
    protected final EventType eventType;
    protected final Instant startTime;
    protected final Instant endTime;
    protected final List<Integer> playerIds;
}
