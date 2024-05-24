package com.korosoft.TribalWarsRemake.domain.mapObjects;

import com.korosoft.TribalWarsRemake.domain.Coordinate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractMapObject {

    private Coordinate coordinate;
    private String image;

}
