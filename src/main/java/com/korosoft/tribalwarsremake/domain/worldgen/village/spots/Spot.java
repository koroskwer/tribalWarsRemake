package com.korosoft.tribalwarsremake.domain.worldgen.village.spots;

import com.korosoft.tribalwarsremake.domain.root.WorldGenerationDirectionPartitionAware;
import com.korosoft.tribalwarsremake.domain.worldgen.WorldGenDirection;
import jakarta.persistence.Entity;
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
public class Spot extends WorldGenerationDirectionPartitionAware<Spot> {

    public Spot(int xCoord, int yCoord, WorldGenDirection direction) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.setPartition(direction);
    }
}
