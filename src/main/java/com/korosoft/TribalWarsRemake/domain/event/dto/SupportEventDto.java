package com.korosoft.TribalWarsRemake.domain.event.dto;

import com.korosoft.TribalWarsRemake.domain.event.EventStatus;
import com.korosoft.TribalWarsRemake.domain.event.EventType;

import java.time.Instant;
import java.util.List;

public class SupportEventDto extends AbstractEventDto{
    public SupportEventDto(EventStatus eventStatus, EventType eventType, Instant startTime, Instant endTime, List<Integer> playerIds) {
        super(eventStatus, eventType, startTime, endTime, playerIds);
    }
}
