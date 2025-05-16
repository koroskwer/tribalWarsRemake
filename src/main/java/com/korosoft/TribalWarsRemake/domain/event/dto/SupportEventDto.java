package com.korosoft.TribalWarsRemake.domain.event.dto;

import com.korosoft.TribalWarsRemake.domain.army.ArmyDto;
import com.korosoft.TribalWarsRemake.domain.event.EventStatus;
import com.korosoft.TribalWarsRemake.domain.event.EventType;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
public class SupportEventDto extends AbstractEventDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private ArmyDto army;
    private int sourceVillageId;
    private int targetVillageId;

    public SupportEventDto(EventStatus eventStatus, EventType eventType, Instant startTime, Instant endTime, List<Integer> playerIds, ArmyDto army, int sourceVillageId, int targetVillageId) {
        super(eventStatus, eventType, startTime, endTime, playerIds);
        this.army = army;
        this.sourceVillageId = sourceVillageId;
        this.targetVillageId = targetVillageId;
    }
}
