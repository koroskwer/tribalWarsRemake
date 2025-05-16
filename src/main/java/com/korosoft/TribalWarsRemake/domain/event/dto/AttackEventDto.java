package com.korosoft.TribalWarsRemake.domain.event.dto;

import com.korosoft.TribalWarsRemake.domain.army.ArmyDto;
import com.korosoft.TribalWarsRemake.domain.event.EventStatus;
import com.korosoft.TribalWarsRemake.domain.event.EventType;
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

    private ArmyDto army;
    private int sourceVillage;
    private int targetVillage;

    public AttackEventDto(EventStatus eventStatus, EventType eventType, Instant startTime, Instant endTime, List<Integer> playerIds, ArmyDto army, int sourceVillage, int targetVillage) {
        super(eventStatus, eventType, startTime, endTime, playerIds);
        this.army = army;
        this.sourceVillage = sourceVillage;
        this.targetVillage = targetVillage;
    }
}
