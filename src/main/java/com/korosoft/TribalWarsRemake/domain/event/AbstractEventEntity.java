package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.root.AbstractEntityRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "events_root")
@NoArgsConstructor
@AllArgsConstructor
public class AbstractEventEntity extends AbstractEntityRoot {

    @Column(name = "status")
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    protected EventStatus eventStatus;

    @Column(name = "event_type")
    @Getter
    @Enumerated(EnumType.STRING)
    protected EventType eventType;

    @Column(name = "start_date")
    protected Instant startDate;

    @Getter
    @Column(name = "finish_date")
    protected Instant finishDate;

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    protected List<Player> involvedPlayers;
}
