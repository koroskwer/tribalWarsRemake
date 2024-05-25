package com.korosoft.TribalWarsRemake.domain.coordinate;

import com.badlogic.gdx.math.Vector2;

public class Coordinate {

    private final Vector2 coordinates;

    public Coordinate(int x, int y){
        this.coordinates = new Vector2(x, y);
    }

    public int getXCoordinate(){
        return (int) this.coordinates.x;
    }

    public int getYCoordinate(){
        return (int) this.coordinates.y;
    }
}
