package com.korosoft.tribalwarsremake.domain.event.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.korosoft.tribalwarsremake.domain.event.EventStatus;
import com.korosoft.tribalwarsremake.domain.event.EventType;
import com.korosoft.tribalwarsremake.domain.resources.ResourcesDto;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
public class TransportEventDto extends AbstractEventDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("resources")
    private ResourcesDto resourcesDto;
    private int sourceVillageId;
    private int targetVillageId;

    public TransportEventDto(ResourcesDto dto, EventStatus eventStatus, EventType eventType, Instant startTime,
                             Instant endTime, List<Integer> playerIds, int sourceVillageId, int targetVillageId) {
        super(eventStatus, eventType, startTime, endTime, playerIds);
        this.resourcesDto = dto;
        this.sourceVillageId = sourceVillageId;
        this.targetVillageId = targetVillageId;
    }
}
