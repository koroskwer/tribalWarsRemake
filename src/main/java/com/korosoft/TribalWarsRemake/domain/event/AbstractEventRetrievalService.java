package com.korosoft.TribalWarsRemake.domain.event;

import java.time.Instant;
import java.util.List;

public interface AbstractEventRetrievalService<T extends AbstractEvent> {
    List<T> getEvents(int playerId, Instant timestamp);
}
