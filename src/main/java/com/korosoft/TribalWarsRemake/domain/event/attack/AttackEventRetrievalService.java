package com.korosoft.TribalWarsRemake.domain.event.attack;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEventRetrievalService;
import com.korosoft.TribalWarsRemake.domain.event.EventStatus;
import com.korosoft.TribalWarsRemake.domain.root.AbstractQueryServiceRoot;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
class AttackEventRetrievalService extends AbstractQueryServiceRoot implements AbstractEventRetrievalService<AttackEvent> {
    @Override
    public List<AttackEvent> getEvents(int playerId, Instant timestamp) {
        return entityManager.createQuery("""
                        select p
                        from AttackEvent p
                        where p.eventRoot.eventStatus = :status and p.eventRoot.finishDate < :timestamp
                        order by p.id
                        """, AttackEvent.class)
                .setParameter("status", EventStatus.READY)
                .setParameter("timestamp", timestamp)
                .unwrap(Query.class)
                .setHibernateLockMode(LockMode.UPGRADE_SKIPLOCKED).getResultList();
    }
}
