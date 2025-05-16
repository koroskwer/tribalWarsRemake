package com.korosoft.TribalWarsRemake.domain.village;

import com.korosoft.TribalWarsRemake.domain.army.Army;
import com.korosoft.TribalWarsRemake.domain.mapObjects.AbstractEntityMapObject;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.resources.Resources;
import com.korosoft.TribalWarsRemake.domain.worldgen.village.spots.Spot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "villages")
@NoArgsConstructor
@Getter
@Setter
public class Village extends AbstractEntityMapObject {

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", nullable = false)
    private Player owner;

    @OneToOne
    @JoinColumn(name = "resources_id", referencedColumnName = "id", nullable = false)
    private Resources resources;

    @OneToMany
    private Set<Army> armies;

    @Column(nullable = false)
    private Instant lastResourceGeneration;

    public Village(Spot spot, Player owner, String name, Resources resources, Instant lastResourceGeneration) {
        this.xCoord = spot.getXCoord();
        this.yCoord = spot.getYCoord();
        this.name = name;
        this.owner = owner;
        this.resources = resources;
        this.armies = new HashSet<>();
        this.lastResourceGeneration = lastResourceGeneration;
    }
}
