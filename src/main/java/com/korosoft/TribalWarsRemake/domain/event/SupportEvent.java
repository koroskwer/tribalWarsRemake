package com.korosoft.TribalWarsRemake.domain.event;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "support_events")
class SupportEvent extends AbstractEvent {

    @Column(name = "pikemen")
    public int pikemen;
}
