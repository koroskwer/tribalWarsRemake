package com.korosoft.TribalWarsRemake.domain.event;

import com.korosoft.TribalWarsRemake.domain.player.Player;

public interface EventProcessor<T extends AbstractEvent> {
    void processEvent(T event, Player player);

    EventType getEventType();
}
