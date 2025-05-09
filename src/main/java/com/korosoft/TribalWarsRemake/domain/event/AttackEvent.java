package com.korosoft.TribalWarsRemake.domain.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "attack_events")
class AttackEvent extends AbstractEvent {

    @Column(name = "defending_pikemen")
    public int defendingPikemen;

    @Column(name = "attacking_pikemen")
    public int attackingPikemen;
}
