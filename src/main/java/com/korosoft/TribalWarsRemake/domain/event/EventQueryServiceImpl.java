package com.korosoft.TribalWarsRemake.domain.event;


import com.korosoft.TribalWarsRemake.domain.root.AbstractQueryServiceRoot;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class EventQueryServiceImpl extends AbstractQueryServiceRoot implements EventQueryService {

    //private final Clock clock;

    public EventQueryServiceImpl() {
        //this.clock = clock;
    }

    @Override
    public List<AbstractEvent> getAllEventsToProcess(int playerId) {
        List<AbstractEvent> list = new ArrayList<>();
        list.addAll(this.getAttackEvents(playerId));
        list.addAll(this.getSupportEvents(playerId));
        list.addAll(this.getTransportEvents(playerId));
        return list;
    }

    @Override
    public List<TransportEvent> getTransportEvents(int playerId) {
        return List.of();
    }

    @Override
    public List<AttackEvent> getAttackEvents(int playerId) {
        return List.of();
    }

    @Override
    public List<SupportEvent> getSupportEvents(int playerId) {
        return List.of();
    }
}
