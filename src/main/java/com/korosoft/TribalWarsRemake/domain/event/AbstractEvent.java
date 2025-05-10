package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.root.AbstractEntityRoot;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@MappedSuperclass
abstract class AbstractEvent extends AbstractEntityRoot {

    @Column(name = "status")
    @Getter
    @Setter
    protected EventStatus eventStatus;

    @Column(name = "event_type")
    @Getter
    protected EventType eventType;

    @Column(name = "start_date")
    protected Instant startDate;

    @Getter
    @Column(name = "finish_date")
    protected Instant finishDate;

    @ManyToMany
    protected List<Player> involvedPlayers;
}
