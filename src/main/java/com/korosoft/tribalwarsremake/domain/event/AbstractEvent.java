package com.korosoft.tribalwarsremake.domain.event;

import com.korosoft.tribalwarsremake.domain.root.AbstractEntityRoot;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Getter
@MappedSuperclass
public class AbstractEvent extends AbstractEntityRoot {
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = false, name = "event_root_id")
    protected AbstractEventEntity eventRoot;
}
