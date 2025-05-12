package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.player.Player;

class ProcessResourceProductionEventService implements ProcessEventService {

    @Override
    public void processEvent(AbstractEventEntity event, Player player) {

    }

    @Override
    public EventType getEventType() {
        return EventType.PRODUCTION;
    }
}
