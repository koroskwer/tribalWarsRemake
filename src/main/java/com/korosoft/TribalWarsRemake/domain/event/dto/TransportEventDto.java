package com.korosoft.TribalWarsRemake.domain.event.dto;

import com.korosoft.TribalWarsRemake.domain.event.EventStatus;
import com.korosoft.TribalWarsRemake.domain.event.EventType;
import com.korosoft.TribalWarsRemake.domain.resources.ResourcesDto;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
public class TransportEventDto extends AbstractEventDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private ResourcesDto resourcesDto;

    public TransportEventDto(EventStatus eventStatus, EventType eventType, Instant startTime, Instant endTime, List<Integer> playerIds) {
        super(eventStatus, eventType, startTime, endTime, playerIds);
        this.resourcesDto = null;
    }
}
