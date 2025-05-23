package com.korosoft.TribalWarsRemake.domain.event.support;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEventRetrievalService;
import com.korosoft.TribalWarsRemake.domain.event.EventStatus;
import com.korosoft.TribalWarsRemake.domain.root.AbstractQueryServiceRoot;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
class SupportEventRetrievalService extends AbstractQueryServiceRoot implements AbstractEventRetrievalService<SupportEvent> {
    @Override
    public List<SupportEvent> getEvents(int playerId, Instant timestamp) {
        return this.entityManager.createQuery(String.format(QUERY_BASE, "SupportEvent"), SupportEvent.class)
                .setParameter("status", EventStatus.READY)
                .setParameter("timestamp", timestamp)
                .unwrap(Query.class)
                .setHibernateLockMode(LockMode.UPGRADE_SKIPLOCKED).getResultList();
    }
}
