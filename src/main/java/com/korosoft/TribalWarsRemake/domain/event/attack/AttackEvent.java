package com.korosoft.TribalWarsRemake.domain.event.attack;

import com.korosoft.TribalWarsRemake.domain.army.Army;
import com.korosoft.TribalWarsRemake.domain.event.AbstractEvent;
import com.korosoft.TribalWarsRemake.domain.event.AbstractEventEntity;
import com.korosoft.TribalWarsRemake.domain.event.EventStatus;
import com.korosoft.TribalWarsRemake.domain.event.EventType;
import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.village.Village;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
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

    AttackEvent(EventStatus status, EventType type, Instant startDate, Instant endDate, List<Player> players, Village targetVillage, Village sourceVillage, Army army) {
        this.eventRoot = new AbstractEventEntity(status, type, startDate, endDate, players);
        this.targetVillage = targetVillage;
        this.sourceVillage = sourceVillage;
        this.army = army;
    }
}
