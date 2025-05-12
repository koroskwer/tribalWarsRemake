package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.root.AbstractEntityRoot;
import jakarta.persistence.CascadeType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Getter
@MappedSuperclass
public class AbstractEventEntity extends AbstractEntityRoot {
    @OneToOne(cascade = CascadeType.ALL)
    protected AbstractEvent eventRoot;
}
