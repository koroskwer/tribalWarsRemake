package com.korosoft.TribalWarsRemake.domain;

public class Coordinate {

    private final int[] coordinates;

    public Coordinate(int x, int y){
        this.coordinates = new int[]{x, y};
    }

    public int getXCoordinate(){
        return this.coordinates[0];
    }

    public int getYCoordinate(){
        return this.coordinates[1];
    }
}
