package com.korosoft.tribalwarsremake.domain.alliance;

import com.korosoft.tribalwarsremake.domain.root.AbstractEntityRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alliances")
@NoArgsConstructor
public class Alliance extends AbstractEntityRoot {

    @Column(nullable = false)
    private String name;
    private String tag;
}
