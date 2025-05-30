package com.korosoft.tribalwarsremake.domain.player;

import com.korosoft.tribalwarsremake.domain.alliance.Alliance;
import com.korosoft.tribalwarsremake.domain.root.AbstractEntityRoot;
import com.korosoft.tribalwarsremake.domain.village.Village;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "players")
@NoArgsConstructor
public class Player extends AbstractEntityRoot {

    @Getter
    @Setter
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "alliance_id")
    private Alliance alliance;

    @OneToMany
    @Getter
    private Set<Village> villages;

    public Player(String name) {
        this.name = name;
    }
}
