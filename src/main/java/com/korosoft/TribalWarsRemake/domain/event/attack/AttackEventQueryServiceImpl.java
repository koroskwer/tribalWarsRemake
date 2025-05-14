package com.korosoft.TribalWarsRemake.domain.event.attack;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEvent;
import com.korosoft.TribalWarsRemake.domain.event.EventStatus;
import com.korosoft.TribalWarsRemake.domain.event.QAttackEvent;
import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import com.korosoft.TribalWarsRemake.domain.root.AbstractQueryServiceRoot;
import lombok.AllArgsConstructor;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@AllArgsConstructor
public class AttackEventQueryServiceImpl extends AbstractQueryServiceRoot implements AttackEventQueryService {

    private static final QAttackEvent Q_Attack_EVENT = QAttackEvent.attackEvent;
    private final AttackEventRepository attackEventRepository;
    private final AttackEventFactory attackEventFactory;

    @Override
    public List<AbstractEvent> getAttackEvents(int playerId, Instant timestamp) {
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

    @Override
    public void addAttackEvent(AttackEventDto AttackEventDto, Instant timestamp) {
        this.attackEventRepository.save(this.attackEventFactory.createAttackEvent());
    }
}
