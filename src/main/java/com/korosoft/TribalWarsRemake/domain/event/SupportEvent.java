package com.korosoft.TribalWarsRemake.domain.event;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "support_events")
@NoArgsConstructor
class SupportEvent extends AbstractEvent {

    @Column(name = "pikemen")
    public int pikemen;

    @OneToOne(cascade = CascadeType.ALL)
    private AbstractEventEntity eventRoot;

    SupportEvent(Instant start, Instant end) {
        this.eventRoot = new AbstractEventEntity(EventStatus.READY, EventType.TRANSPORT, start, end, List.of());
    }
}
