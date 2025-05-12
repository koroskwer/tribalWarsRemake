package com.korosoft.TribalWarsRemake.domain.army;

import com.korosoft.TribalWarsRemake.domain.root.AbstractEntityRoot;
import com.korosoft.TribalWarsRemake.domain.village.Village;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "armies")
@AllArgsConstructor
@NoArgsConstructor
public class Army extends AbstractEntityRoot {

    private int pikemen;

    @ManyToOne
    @JoinColumn(name = "village_id", nullable = false)
    private Village sourceVillage;
}
