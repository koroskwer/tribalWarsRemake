package com.korosoft.tribalwarsremake.domain.event.transport;

import com.korosoft.tribalwarsremake.domain.event.AbstractEventRetrievalService;
import com.korosoft.tribalwarsremake.domain.event.EventStatus;
import com.korosoft.tribalwarsremake.domain.root.AbstractQueryServiceRoot;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
class TransportEventRetrievalService extends AbstractQueryServiceRoot implements AbstractEventRetrievalService<TransportEvent> {
    @Override
    public List<TransportEvent> getEvents(int playerId, Instant timestamp) {
        return this.entityManager.createQuery(String.format(QUERY_BASE, "TransportEvent"), TransportEvent.class)
                .setParameter("status", EventStatus.READY)
                .setParameter("timestamp", timestamp)
                .unwrap(Query.class)
                .setHibernateLockMode(LockMode.UPGRADE_SKIPLOCKED).getResultList();
    }
}
