package com.korosoft.TribalWarsRemake.domain.event.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.korosoft.TribalWarsRemake.domain.event.EventStatus;
import com.korosoft.TribalWarsRemake.domain.event.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEventDto {
    protected EventStatus eventStatus;
    protected EventType eventType;
    @JsonIgnore
    protected Instant startTime;
    @JsonIgnore
    protected Instant endTime;
    protected List<Integer> playerIds;
}
