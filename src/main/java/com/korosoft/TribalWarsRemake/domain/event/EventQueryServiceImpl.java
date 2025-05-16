package com.korosoft.TribalWarsRemake.domain.event;


import com.korosoft.TribalWarsRemake.domain.event.attack.AttackEventFacade;
import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.SupportEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;
import com.korosoft.TribalWarsRemake.domain.event.support.SupportEventFacade;
import com.korosoft.TribalWarsRemake.domain.event.transport.TransportEventFacade;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.root.AbstractQueryServiceRoot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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

    @Override
    public void addAttackEvent(AttackEventDto attackEventDto, Instant timestamp) {
        // TODO remove army from village and validate if enough troops are present to send the attack
        // TODO add generation resource event before adding other event
        this.attackEventFacade.addAttackEvent(attackEventDto, timestamp);
    }

    @Override
    public void addSupportEvent(SupportEventDto supportEventDto, Instant timestamp) {
        // TODO remove army from village and validate if enough troops are present to send the support
        // TODO add generation resource event before adding other event
        this.supportEventFacade.addSupportEvent(supportEventDto, timestamp);
    }

    @Override
    public void addTransportEvent(TransportEventDto transportEventDto, Instant timestamp) {
        // TODO remove resources from village and check if there are enough resources to send
        // TODO add generation resource event before adding other event
        this.transportEventFacade.addTransportEvent(transportEventDto, timestamp);
    }

    @Override
    public void deleteEvents(int playerId, Instant timestamp) {
        //TODO log result of this query
        this.getQueryFactory().delete(Q_ABSTRACT_EVENT)
                .where(Q_ABSTRACT_EVENT.finishDate.before(timestamp)
                        .and(Q_ABSTRACT_EVENT.involvedPlayers.contains((this.entityManager.getReference(Player.class, playerId)))
                                .and(Q_ABSTRACT_EVENT.eventStatus.eq(EventStatus.READY)))).execute();
    }
}
