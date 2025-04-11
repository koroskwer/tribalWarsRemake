package com.korosoft.TribalWarsRemake.domain.alliance;

import com.korosoft.TribalWarsRemake.domain.root.AbstractEntityRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alliances")
@NoArgsConstructor
public class Alliance extends AbstractEntityRoot {

    private String name;
    private String tag;
}
