package com.korosoft.TribalWarsRemake.domain.resources;


import com.korosoft.TribalWarsRemake.domain.root.AbstractEntityRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "resources")
@NoArgsConstructor
@Getter
@Setter
public class Resources extends AbstractEntityRoot {

    @Column
    private int wood;
    @Column
    private int clay;
    @Column
    private int iron;

    Resources(final int wood, final int clay, final int iron) {
        this.wood = wood;
        this.clay = clay;
        this.iron = iron;
    }
}
