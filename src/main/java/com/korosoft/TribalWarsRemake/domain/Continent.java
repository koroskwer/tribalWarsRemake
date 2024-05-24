package com.korosoft.TribalWarsRemake.domain;

import com.korosoft.TribalWarsRemake.domain.mapObjects.AbstractMapObject;
import com.korosoft.TribalWarsRemake.domain.mapObjects.Scenery;

public class Continent {

    private int world;
    private int number;
    private int xOffset;
    private int yOffset;
    private int numberOfPlayerVillages;
    private int numberOfBarbVillages;
    private AbstractMapObject[][] mapObjects;
    private final Coordinate topLeftCoordinatesBounds;
    private final Coordinate downRightCoordinatesBounds;

    public Continent(int number, int world) {
        this.world = world;
        this.number = number;
        this.xOffset = number % 10;
        this.yOffset = number / 10;
        this.numberOfBarbVillages = 0;
        this.numberOfPlayerVillages = 0;


        this.topLeftCoordinatesBounds = this.findInitialTopLeftCoordinates(xOffset, yOffset);
        this.downRightCoordinatesBounds = this.findInitialTopLeftCoordinates(xOffset, yOffset);

        this.fillInitialMap();
    }

    private void fillInitialMap() {
        this.mapObjects = new AbstractMapObject[100][100];
        for (int coordX = 0; coordX < 100; coordX++) {
            for (int coordY = 0; coordY < 100; coordY++) {
                this.mapObjects[coordX][coordY] = new Scenery(new Coordinate(coordX * this.xOffset, coordY * this.yOffset), "placeholderImageUrl");
            }
        }
    }

    private Coordinate findInitialTopLeftCoordinates(int numberX, int numberY) {
        return new Coordinate(100 * numberY, 100 * numberX);
    }

    private Coordinate findInitialDownRightCoordinates(int numberX, int numberY) {
        return new Coordinate(100 * numberY + 99, 100 * numberX + 99);
    }
}
