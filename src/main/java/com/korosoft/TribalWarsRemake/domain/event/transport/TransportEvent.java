package com.korosoft.TribalWarsRemake.domain.event.transport;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEvent;
import com.korosoft.TribalWarsRemake.domain.event.AbstractEventEntity;
import com.korosoft.TribalWarsRemake.domain.event.EventStatus;
import com.korosoft.TribalWarsRemake.domain.event.EventType;
import com.korosoft.TribalWarsRemake.domain.resources.Resources;
import com.korosoft.TribalWarsRemake.domain.village.Village;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

    @OneToOne
    @JoinColumn(name = "resources_id", referencedColumnName = "id", nullable = false)
    private Resources resources;
    @OneToOne
    private Village targetVillage;
    @OneToOne
    private Village sourceVillage;

    TransportEvent(Instant start, Instant end, Resources resources) {
        this.eventRoot = new AbstractEventEntity(EventStatus.READY, EventType.TRANSPORT, start, end, List.of());
        this.resources = resources;
    }
}
