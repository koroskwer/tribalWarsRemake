package com.korosoft.tribalwarsremake.domain.root;

import com.korosoft.tribalwarsremake.domain.mapObjects.AbstractEntityMapObject;
import com.korosoft.tribalwarsremake.domain.worldgen.WorldGenDirection;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.PartitionKey;

@Getter
@MappedSuperclass
@FilterDef(
        name = WorldGenerationDirectionPartitionAware.PARTITION_KEY,
        parameters = @ParamDef(
                name = WorldGenerationDirectionPartitionAware.PARTITION_KEY,
                type = String.class
        )
)
@Filter(
        name = WorldGenerationDirectionPartitionAware.PARTITION_KEY,
        condition = "partition_key = :partitionKey"
)
public abstract class WorldGenerationDirectionPartitionAware<T extends WorldGenerationDirectionPartitionAware> extends AbstractEntityMapObject {

    public static final String PARTITION_KEY = "partitionKey";

    @Column(name = "partition_key")
    @PartitionKey
    private String partitionKey;

    public T setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
        return (T) this;
    }

    public T setPartition(WorldGenDirection direction) {
        this.partitionKey = direction.getKey();
        return (T) this;
    }
}
