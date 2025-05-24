package com.korosoft.tribalwarsremake.domain.event.transport;

import com.korosoft.tribalwarsremake.domain.event.AbstractEvent;
import com.korosoft.tribalwarsremake.domain.event.AbstractEventEntity;
import com.korosoft.tribalwarsremake.domain.event.EventStatus;
import com.korosoft.tribalwarsremake.domain.event.EventType;
import com.korosoft.tribalwarsremake.domain.player.Player;
import com.korosoft.tribalwarsremake.domain.resources.Resources;
import com.korosoft.tribalwarsremake.domain.village.Village;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "transport_events")
@NoArgsConstructor
@Getter
@Setter
class TransportEvent extends AbstractEvent {

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "resources_id", referencedColumnName = "id")
    @Setter
    private Resources resources;
    @OneToOne
    private Village targetVillage;
    @OneToOne
    private Village sourceVillage;

    TransportEvent(Instant start, Instant end, Resources resources) {
        this.eventRoot = new AbstractEventEntity(EventStatus.READY, EventType.TRANSPORT, start, end, List.of());
        this.resources = resources;
    }

    TransportEvent(EventStatus eventStatus, EventType eventType, Instant start, Instant end, List<Player> involvedPlayers,
                   Resources resources, Village targetVillage, Village sourceVillage) {
        this.eventRoot = new AbstractEventEntity(eventStatus, eventType, start, end, involvedPlayers);
        this.resources = resources;
        this.targetVillage = targetVillage;
        this.sourceVillage = sourceVillage;
    }
}
