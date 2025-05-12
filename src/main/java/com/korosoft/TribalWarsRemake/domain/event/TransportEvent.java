package com.korosoft.TribalWarsRemake.domain.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "transport_events")
@NoArgsConstructor
class TransportEvent extends AbstractEvent {

    @Column(name = "wood")
    public int wood;
    @Column(name = "clay")
    public int clay;
    @Column(name = "iron")
    public int iron;

    TransportEvent(Instant start, Instant end) {
        this.startDate = start;
        this.finishDate = end;
    }
}
