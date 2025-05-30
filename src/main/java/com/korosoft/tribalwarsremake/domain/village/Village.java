package com.korosoft.tribalwarsremake.domain.village;

import com.korosoft.tribalwarsremake.domain.army.Army;
import com.korosoft.tribalwarsremake.domain.mapObjects.AbstractEntityMapObject;
import com.korosoft.tribalwarsremake.domain.player.Player;
import com.korosoft.tribalwarsremake.domain.resources.Resources;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.Spot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
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

    @OneToOne(cascade = CascadeType.PERSIST)
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
        //TODO remove this arbitrary amount of units after developing unit creation events
        this.armies = Set.of(new Army(1000, this));
        this.lastResourceGeneration = lastResourceGeneration;
    }
}
