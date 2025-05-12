package com.korosoft.TribalWarsRemake.domain.event.transport;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEvent;
import com.korosoft.TribalWarsRemake.domain.event.AbstractEventEntity;
import com.korosoft.TribalWarsRemake.domain.event.EventStatus;
import com.korosoft.TribalWarsRemake.domain.event.EventType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "transport_events")
@NoArgsConstructor
class TransportEvent extends AbstractEventEntity {

    @Column(name = "wood")
    public int wood;
    @Column(name = "clay")
    public int clay;
    @Column(name = "iron")
    public int iron;

    TransportEvent(Instant start, Instant end) {
        this.eventRoot = new AbstractEvent(EventStatus.READY, EventType.TRANSPORT, start, end, List.of());
    }
}
