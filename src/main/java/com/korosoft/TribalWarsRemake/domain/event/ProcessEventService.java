package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.player.Player;

interface ProcessEventService {
    void processEvent(AbstractEvent event, Player player);

    EventType getEventType();
}
