package com.korosoft.TribalWarsRemake.domain.player;

import com.korosoft.TribalWarsRemake.domain.alliance.Alliance;
import com.korosoft.TribalWarsRemake.domain.root.AbstractEntityRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "players")
@NoArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "players_id_seq", allocationSize = 1)
public class Player extends AbstractEntityRoot {

    @Getter
    @Setter
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "alliance_id")
    private Alliance alliance;

    public Player(String name) {
        this.name = name;
    }
}
