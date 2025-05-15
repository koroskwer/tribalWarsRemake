package com.korosoft.TribalWarsRemake.domain.event.support;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEvent;
import com.korosoft.TribalWarsRemake.domain.event.dto.SupportEventDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class SupportEventFacade {

    private final SupportEventRetrievalService supportEventRetrievalService;
    private final SupportEventCreationService supportEventCreationService;

    public List<AbstractEvent> getSupportEvents(int playerId, Instant timestamp) {
        return new ArrayList<>(this.supportEventRetrievalService.getEvents(playerId, timestamp));
    }

    public void addSupportEvent(SupportEventDto SupportEventDto, Instant timestamp) {
        this.supportEventCreationService.addEvent(SupportEventDto, timestamp);
    }
}
