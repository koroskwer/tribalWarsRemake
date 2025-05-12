package com.korosoft.TribalWarsRemake.domain.resources;


import com.korosoft.TribalWarsRemake.domain.root.AbstractEntityRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
