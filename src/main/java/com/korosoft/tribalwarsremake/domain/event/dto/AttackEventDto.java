package com.korosoft.tribalwarsremake.domain.event.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.korosoft.tribalwarsremake.domain.army.ArmyDto;
import com.korosoft.tribalwarsremake.domain.event.EventStatus;
import com.korosoft.tribalwarsremake.domain.event.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AttackEventDto extends AbstractEventDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("army")
    private ArmyDto army;
    private int sourceVillageId;
    private int targetVillageId;

    public AttackEventDto(EventStatus eventStatus, EventType eventType, Instant startTime, Instant endTime, List<Integer> playerIds, ArmyDto army, int sourceVillageId, int targetVillageId) {
        super(eventStatus, eventType, startTime, endTime, playerIds);
        this.army = army;
        this.sourceVillageId = sourceVillageId;
        this.targetVillageId = targetVillageId;
    }
}
