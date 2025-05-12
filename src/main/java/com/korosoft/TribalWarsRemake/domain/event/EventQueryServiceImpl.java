package com.korosoft.TribalWarsRemake.domain.event;


import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.SupportEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.root.AbstractQueryServiceRoot;
import com.korosoft.TribalWarsRemake.domain.village.Village;
import lombok.AllArgsConstructor;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
class EventQueryServiceImpl extends AbstractQueryServiceRoot implements EventQueryService {

    private static final QAttackEvent Q_ATTACK_EVENT = QAttackEvent.attackEvent;
    private static final QTransportEvent Q_TRANSPORT_EVENT = QTransportEvent.transportEvent;
    private static final QSupportEvent Q_SUPPORT_EVENT = QSupportEvent.supportEvent;
    private final AttackEventRepository attackEventRepository;
    private final TransportEventRepository transportEventRepository;

    @Override
    public List<AbstractEvent> getAllEventsToProcess(int playerId, Instant timestamp) {
        List<AbstractEvent> list = new ArrayList<>();
        list.addAll(this.getAttackEvents(playerId, timestamp));
        list.addAll(this.getSupportEvents(playerId, timestamp));
        list.addAll(this.getTransportEvents(playerId, timestamp));
        return list;
    }

    @Override
    public List<TransportEvent> getTransportEvents(int playerId, Instant timestamp) {
        return entityManager.createQuery("""
                        select p
                        from TransportEvent p
                        where p.eventStatus = :status and p.finishDate = :timestamp
                        order by p.id
                        """, TransportEvent.class)
                .setParameter("status", EventStatus.READY)
                .setParameter("timestamp", timestamp)
                .unwrap(Query.class)
                .setHibernateLockMode(LockMode.UPGRADE_SKIPLOCKED).getResultList();
    }

    @Override
    public List<AttackEvent> getAttackEvents(int playerId, Instant timestamp) {
        return this.getQueryFactory().from(Q_ATTACK_EVENT)
                .where(Q_ATTACK_EVENT.eventStatus.eq(EventStatus.READY)
                        .and(Q_ATTACK_EVENT.finishDate.before(timestamp)
                                .and(Q_ATTACK_EVENT.involvedPlayers.contains(entityManager.getReference(Player.class, playerId)))))
                .select(Q_ATTACK_EVENT).fetch();
    }

    @Override
    public List<SupportEvent> getSupportEvents(int playerId, Instant timestamp) {
        return List.of();
    }

    @Override
    public void addAttackEvent(AttackEventDto attackEventDto, Instant timestamp) {
        Village targetVillage = this.entityManager.getReference(Village.class, attackEventDto.targetVillageId());
        Village sourceVillage = this.entityManager.getReference(Village.class, attackEventDto.sourceVillageId());
        AttackEvent event = new AttackEvent(attackEventDto, timestamp, timestamp, targetVillage, sourceVillage);
        this.attackEventRepository.save(event);
    }

    @Override
    public void addSupportEvent(SupportEventDto supportEventDto, Instant timestamp) {

    }

    @Override
    public void addTransportEvent(TransportEventDto transportEventDto, Instant timestamp) {
        this.transportEventRepository.save(new TransportEvent(timestamp, timestamp));
    }
}
