package com.korosoft.TribalWarsRemake.domain.worldgen.village.spots.impl;

import com.korosoft.TribalWarsRemake.domain.root.AbstractQueryServiceRoot;
import com.korosoft.TribalWarsRemake.domain.worldgen.WorldGenDirection;
import com.korosoft.TribalWarsRemake.domain.worldgen.village.spots.QSpot;
import com.korosoft.TribalWarsRemake.domain.worldgen.village.spots.Spot;
import com.korosoft.TribalWarsRemake.domain.worldgen.village.spots.SpotQueryService;
import com.korosoft.TribalWarsRemake.domain.worldgen.village.spots.generationlevel.QSpotGenerationLevel;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SpotQueryServiceImpl extends AbstractQueryServiceRoot implements SpotQueryService {

    private static final QSpot SPOT = QSpot.spot;
    private static final QSpotGenerationLevel SPOT_GENERATION_LEVEL = QSpotGenerationLevel.spotGenerationLevel;

    @Override
    public Collection<Spot> getAllSpots(WorldGenDirection direction) {
        return this.getQueryFactoryWithPartitionKey(direction.getKey()).selectFrom(SPOT).fetch();
    }

    @Override
    public Long getCountOfSpots(WorldGenDirection direction) {
        return this.getQueryFactoryWithPartitionKey(direction.getKey())
                .from(SPOT)
                .select(SPOT.count())
                .fetchOne();
    }

    @Override
    public Long getMinimumSpots(WorldGenDirection direction) {
        return this.getQueryFactory()
                .from(SPOT_GENERATION_LEVEL)
                .where(SPOT_GENERATION_LEVEL.direction.eq(direction.getKey()))
                .select(SPOT_GENERATION_LEVEL.minimumAmountOfSpots)
                .fetchOne();
    }

    public void deleteRedundantSpots(WorldGenDirection direction) {
        this.getQueryFactoryWithPartitionKey(direction.getKey())
                .delete(SPOT)
                .execute();
    }

    public Boolean areEnoughSpotsLeft(WorldGenDirection direction) {
        /*
        select (count(spots_south_east) < minimum_amount_of_spots) from spots_south_east
join spot_generation_levels on partition_key = world_generation_direction
group by minimum_amount_of_spots
         */
        return this.getQueryFactoryWithPartitionKey(direction.getKey())
                .from(SPOT)
                .join(SPOT_GENERATION_LEVEL)
                .on(SPOT.partitionKey.eq(SPOT_GENERATION_LEVEL.direction))
                .select(SPOT.count().gt(SPOT_GENERATION_LEVEL.minimumAmountOfSpots))
                .groupBy(SPOT_GENERATION_LEVEL.minimumAmountOfSpots).fetchOne();
    }

    @Override
    public Spot getRandomSpot(WorldGenDirection direction) {
        return this.getQueryFactoryWithPartitionKey(direction.getKey())
                .selectFrom(SPOT)
                .orderBy(Expressions.stringTemplate("random()").asc())
                .limit(1)
                .fetchOne();
    }
}
