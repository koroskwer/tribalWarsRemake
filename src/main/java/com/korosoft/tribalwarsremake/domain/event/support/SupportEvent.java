package com.korosoft.tribalwarsremake.domain.event.support;


import com.korosoft.tribalwarsremake.domain.army.Army;
import com.korosoft.tribalwarsremake.domain.event.AbstractEvent;
import com.korosoft.tribalwarsremake.domain.event.AbstractEventEntity;
import com.korosoft.tribalwarsremake.domain.event.EventStatus;
import com.korosoft.tribalwarsremake.domain.event.EventType;
import com.korosoft.tribalwarsremake.domain.player.Player;
import com.korosoft.tribalwarsremake.domain.village.Village;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "support_events")
@NoArgsConstructor
@Getter
@Setter
class SupportEvent extends AbstractEvent {

    @OneToOne
    private Army army;
    @OneToOne
    private Village targetVillage;
    @OneToOne
    private Village sourceVillage;

    SupportEvent(EventStatus status, EventType type, Instant startDate, Instant endDate, List<Player> players, Village targetVillage, Village sourceVillage, Army army) {
        this.eventRoot = new AbstractEventEntity(status, type, startDate, endDate, players);
        this.targetVillage = targetVillage;
        this.sourceVillage = sourceVillage;
        this.army = army;
    }
}
