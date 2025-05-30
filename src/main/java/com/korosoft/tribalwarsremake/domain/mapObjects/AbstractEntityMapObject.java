package com.korosoft.tribalwarsremake.domain.mapObjects;

import com.korosoft.tribalwarsremake.domain.root.AbstractEntityRoot;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
public abstract class AbstractEntityMapObject extends AbstractEntityRoot {

    @Getter
    @Column(name = "x_coord", nullable = false)
    protected int xCoord;
    @Getter
    @Column(name = "y_coord", nullable = false)
    protected int yCoord;
}
