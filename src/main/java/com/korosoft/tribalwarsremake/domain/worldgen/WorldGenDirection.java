package com.korosoft.tribalwarsremake.domain.worldgen;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum WorldGenDirection implements Serializable {
    NORTH_EAST("north-east"), NORTH_WEST("north-west"), SOUTH_EAST("south-east"), SOUTH_WEST("south-west");

    private final String key;

    WorldGenDirection(String key) {
        this.key = key;
    }
}
