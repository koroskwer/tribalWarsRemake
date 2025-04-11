package com.korosoft.TribalWarsRemake.domain.worldgen.village.spots;

import com.korosoft.TribalWarsRemake.domain.root.WorldGenerationDirectionPartitionAware;
import com.korosoft.TribalWarsRemake.domain.worldgen.WorldGenDirection;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;


/**
 * Class used to represent available spots for village creation on the game map. Spots are divided into four world quadrants
 *
 * @see WorldGenDirection
 */
@Entity
@Table(name = "spots")
@NoArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "spots_id_seq", allocationSize = 1)
public class Spot extends WorldGenerationDirectionPartitionAware<Spot> {

    public Spot(int xCoord, int yCoord, WorldGenDirection direction) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.setPartition(direction);
    }
}
