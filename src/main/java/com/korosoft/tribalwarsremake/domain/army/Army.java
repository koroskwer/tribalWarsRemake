package com.korosoft.tribalwarsremake.domain.army;

import com.korosoft.tribalwarsremake.domain.root.AbstractEntityRoot;
import com.korosoft.tribalwarsremake.domain.village.Village;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
