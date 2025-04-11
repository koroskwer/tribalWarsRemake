package com.korosoft.TribalWarsRemake.domain.worldgen.village.spots.generationlevel;

import com.korosoft.TribalWarsRemake.config.GameConstants;
import com.korosoft.TribalWarsRemake.domain.root.AbstractEntityRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "spot_generation_levels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpotGenerationLevel extends AbstractEntityRoot {

    @Column(name = "generation_level")
    private int generationLevel;

    @Column(name = "minimum_amount_of_spots")
    private Long minimumAmountOfSpots;

    @Column(name = "world_generation_direction")
    private String direction;

    public void increaseSpotGenerationLevel(int spotsListSize) {
        this.generationLevel++;
        this.minimumAmountOfSpots = spotsListSize * GameConstants.MAP_FILLED_PERCENTAGE / 100;
    }
}
