package com.korosoft.TribalWarsRemake.domain.army;

import com.korosoft.TribalWarsRemake.domain.root.AbstractEntityRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Entity
@Table(name = "armies")
@AllArgsConstructor
public class Army extends AbstractEntityRoot {

    private int pikemen;
}
