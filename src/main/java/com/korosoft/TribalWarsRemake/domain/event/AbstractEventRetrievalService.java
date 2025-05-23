package com.korosoft.TribalWarsRemake.domain.event;

import java.time.Instant;
import java.util.List;

/**
 * Interface used to retrieve list of events that have been unprocessed before given timestamp
 * @param <T> Class that extends {@link AbstractEvent}
 */
public interface AbstractEventRetrievalService<T extends AbstractEvent> {
    String QUERY_BASE = """
                        select p
                        from %s p
                        where p.eventRoot.eventStatus = :status and p.eventRoot.finishDate < :timestamp
                        order by p.id
                        """;

    List<T> getEvents(int playerId, Instant timestamp);
}
