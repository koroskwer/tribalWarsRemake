package com.korosoft.TribalWarsRemake.domain.event;


import com.korosoft.TribalWarsRemake.domain.event.attack.AttackEventFacade;
import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.SupportEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;
import com.korosoft.TribalWarsRemake.domain.event.support.SupportEventFacade;
import com.korosoft.TribalWarsRemake.domain.event.transport.TransportEventFacade;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.resources.ResourcesFacade;
import com.korosoft.TribalWarsRemake.domain.root.AbstractQueryServiceRoot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
class EventQueryServiceImpl extends AbstractQueryServiceRoot implements EventQueryService {

    private static final QAbstractEventEntity Q_ABSTRACT_EVENT = QAbstractEventEntity.abstractEventEntity;
    private final TransportEventFacade transportEventFacade;
    private final AttackEventFacade attackEventFacade;
    private final SupportEventFacade supportEventFacade;

    @Override
    public List<AbstractEvent> getAllEventsToProcess(int playerId, Instant timestamp) {
        List<AbstractEvent> list = new ArrayList<>();
        //TODO maybe make this part concurrent?
        list.addAll(this.attackEventFacade.getAttackEvents(playerId, timestamp));
        list.addAll(this.transportEventFacade.getTransportEvents(playerId, timestamp));
        list.addAll(this.supportEventFacade.getSupportEvents(playerId, timestamp));
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addAttackEvent(AttackEventDto attackEventDto, Instant timestamp) {
        this.attackEventFacade.addAttackEvent(attackEventDto, timestamp);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addSupportEvent(SupportEventDto supportEventDto, Instant timestamp) {
        this.supportEventFacade.addSupportEvent(supportEventDto, timestamp);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addTransportEvent(TransportEventDto transportEventDto, Instant timestamp) {
        this.transportEventFacade.addTransportEvent(transportEventDto, timestamp);
    }

    @Override
    public void deleteEvents(int playerId, Instant timestamp) {
        //TODO log result of this query

        /*  //TODO add this later
       .and(Q_ABSTRACT_EVENT.involvedPlayers.contains((this.entityManager.getReference(Player.class, playerId)))
         */
        this.getQueryFactory().delete(Q_ABSTRACT_EVENT)
                .where(Q_ABSTRACT_EVENT.finishDate.before(timestamp)
                                .and(Q_ABSTRACT_EVENT.eventStatus.eq(EventStatus.READY))).execute();
    }
}
