package com.korosoft.TribalWarsRemake.domain.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "transport_events")
class TransportEvent extends AbstractEvent {

    @Column(name = "wood")
    public int wood;

    @Column(name = "clay")
    public int clay;

    @Column(name = "iron")
    public int iron;
}
