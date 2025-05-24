package com.korosoft.tribalwarsremake.domain.event;

import com.korosoft.tribalwarsremake.domain.player.Player;
import com.korosoft.tribalwarsremake.domain.root.AbstractEntityRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    protected List<Player> involvedPlayers;
}
