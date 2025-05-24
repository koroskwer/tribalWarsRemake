package com.korosoft.tribalwarsremake.domain.event.attack;

import com.korosoft.tribalwarsremake.domain.event.AbstractEventRetrievalService;
import com.korosoft.tribalwarsremake.domain.event.EventStatus;
import com.korosoft.tribalwarsremake.domain.root.AbstractQueryServiceRoot;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
class AttackEventRetrievalService extends AbstractQueryServiceRoot implements AbstractEventRetrievalService<AttackEvent> {
    @Override
    public List<AttackEvent> getEvents(int playerId, Instant timestamp) {
        return this.entityManager.createQuery(String.format(QUERY_BASE, "AttackEvent"), AttackEvent.class)
                .setParameter("status", EventStatus.READY)
                .setParameter("timestamp", timestamp)
                .unwrap(Query.class)
                .setHibernateLockMode(LockMode.UPGRADE_SKIPLOCKED).getResultList();
    }
}
