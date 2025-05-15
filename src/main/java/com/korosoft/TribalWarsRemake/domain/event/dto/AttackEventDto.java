package com.korosoft.TribalWarsRemake.domain.event.dto;

import com.korosoft.TribalWarsRemake.domain.event.EventStatus;
import com.korosoft.TribalWarsRemake.domain.event.EventType;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
public class AttackEventDto extends AbstractEventDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final int pikemen;
    private final int sourceVillage;
    private final int targetVillage;

    public AttackEventDto(EventStatus eventStatus, EventType eventType, Instant startTime, Instant endTime, List<Integer> playerIds, int pikemen, int sourceVillage, int targetVillage) {
        super(eventStatus, eventType, startTime, endTime, playerIds);
        this.pikemen = pikemen;
        this.sourceVillage = sourceVillage;
        this.targetVillage = targetVillage;
    }
}
