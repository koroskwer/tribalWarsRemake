package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.root.AbstractEntityRoot;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Getter
@MappedSuperclass
public class AbstractEvent extends AbstractEntityRoot {
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "event_root_id")
    protected AbstractEventEntity eventRoot;
}
