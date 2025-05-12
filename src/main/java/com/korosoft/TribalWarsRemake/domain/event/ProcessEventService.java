package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.player.Player;

public interface ProcessEventService {
    void processEvent(AbstractEventEntity event, Player player);

    EventType getEventType();
}
