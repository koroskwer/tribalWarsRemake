package com.korosoft.tribalwarsremake.domain.worldgen.village.spots.impl;

import com.korosoft.tribalwarsremake.domain.root.AbstractQueryServiceRoot;
import com.korosoft.tribalwarsremake.domain.worldgen.WorldGenDirection;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.QSpot;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.Spot;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.SpotQueryService;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.generationlevel.QSpotGenerationLevel;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

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

    /**
     * select (count(spots_south_east) < minimum_amount_of_spots) from spots_south_east
     * join spot_generation_levels on partition_key = world_generation_direction
     * group by minimum_amount_of_spots
     */
    public Boolean areEnoughSpotsLeft(WorldGenDirection direction, int amount) {
        return this.getQueryFactoryWithPartitionKey(direction.getKey())
                .from(SPOT_GENERATION_LEVEL)
                .leftJoin(SPOT)
                .on(SPOT_GENERATION_LEVEL.direction.eq(SPOT.partitionKey))
                .where(SPOT_GENERATION_LEVEL.direction.eq(direction.getKey()))
                .select(SPOT.count().gt(SPOT_GENERATION_LEVEL.minimumAmountOfSpots.add(amount)))
                .groupBy(SPOT_GENERATION_LEVEL.minimumAmountOfSpots).fetchOne();
    }

    @Override
    public List<Spot> getRandomSpots(WorldGenDirection direction, int amount) {
        return this.getQueryFactoryWithPartitionKey(direction.getKey())
                .selectFrom(SPOT)
                .orderBy(Expressions.stringTemplate("random()").asc())
                .limit(amount)
                .fetch();
    }
}
