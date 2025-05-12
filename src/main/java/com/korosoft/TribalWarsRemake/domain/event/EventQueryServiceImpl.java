package com.korosoft.TribalWarsRemake.domain.event;


import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.SupportEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;
import com.korosoft.TribalWarsRemake.domain.event.transport.TransportEventFacade;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.root.AbstractQueryServiceRoot;
import com.korosoft.TribalWarsRemake.domain.village.Village;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
class EventQueryServiceImpl extends AbstractQueryServiceRoot implements EventQueryService {

    private static final QAttackEvent Q_ATTACK_EVENT = QAttackEvent.attackEvent;
    private static final QSupportEvent Q_SUPPORT_EVENT = QSupportEvent.supportEvent;
    private final TransportEventFacade transportEventFacade;
    private final AttackEventRepository attackEventRepository;

    @Override
    public List<AbstractEvent> getAllEventsToProcess(int playerId, Instant timestamp) {
        List<AbstractEvent> list = new ArrayList<>();
        list.addAll(this.getAttackEvents(playerId, timestamp));
        list.addAll(this.getSupportEvents(playerId, timestamp));
        list.addAll(this.transportEventFacade.getTransportEvents(playerId, timestamp));
        return list;
    }


    @Override
    public List<AttackEvent> getAttackEvents(int playerId, Instant timestamp) {
        return this.getQueryFactory().from(Q_ATTACK_EVENT)
                .where(Q_ATTACK_EVENT.eventRoot.eventStatus.eq(EventStatus.READY)
                        .and(Q_ATTACK_EVENT.eventRoot.finishDate.before(timestamp)
                                .and(Q_ATTACK_EVENT.eventRoot.involvedPlayers.contains(entityManager.getReference(Player.class, playerId)))))
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
        this.transportEventFacade.addTransportEvent(transportEventDto, timestamp);
    }
}
