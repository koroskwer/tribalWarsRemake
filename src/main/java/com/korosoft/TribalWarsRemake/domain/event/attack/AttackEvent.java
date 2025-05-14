package com.korosoft.TribalWarsRemake.domain.event.attack;

import com.korosoft.TribalWarsRemake.domain.army.Army;
import com.korosoft.TribalWarsRemake.domain.event.AbstractEvent;
import com.korosoft.TribalWarsRemake.domain.event.AbstractEventEntity;
import com.korosoft.TribalWarsRemake.domain.event.EventStatus;
import com.korosoft.TribalWarsRemake.domain.event.EventType;
import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import com.korosoft.TribalWarsRemake.domain.village.Village;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@Entity
@Table(name = "attack_events")
@NoArgsConstructor
class AttackEvent extends AbstractEvent {

    @OneToOne
    private Army army;
    @OneToOne
    private Village targetVillage;
    @OneToOne
    private Village sourceVillage;

    AttackEvent(AttackEventDto dto, Instant startDate, Instant endDate, Village targetVillage, Village sourceVillage) {
        this.eventRoot = new AbstractEventEntity(EventStatus.READY, EventType.ATTACK, startDate, endDate, List.of());
        this.army = new Army(dto.pikemen(), sourceVillage);
        this.targetVillage = targetVillage;
        this.sourceVillage = sourceVillage;
    }
}
