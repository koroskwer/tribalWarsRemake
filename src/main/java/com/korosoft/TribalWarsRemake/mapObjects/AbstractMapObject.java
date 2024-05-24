package com.korosoft.TribalWarsRemake.mapObjects;

import com.korosoft.TribalWarsRemake.Coordinate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractMapObject {

    private Coordinate coordinate;
    private String image;

}
