package com.korosoft.TribalWarsRemake.domain.village;

import com.korosoft.TribalWarsRemake.domain.army.Army;
import com.korosoft.TribalWarsRemake.domain.mapObjects.AbstractEntityMapObject;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.worldgen.village.spots.Spot;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "villages")
@NoArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "villages_id_seq", allocationSize = 1)
public class Village extends AbstractEntityMapObject {

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", nullable = false)
    private Player owner;

    @OneToMany
    private Set<Army> armies;

    public Village(Spot spot, Player owner, String name) {
        this.xCoord = spot.getXCoord();
        this.yCoord = spot.getYCoord();
        this.name = name;
        this.owner = owner;
    }
}
