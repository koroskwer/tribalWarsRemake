package com.korosoft.tribalwarsremake.domain.event;

import com.korosoft.tribalwarsremake.domain.player.Player;

public interface EventProcessor<T extends AbstractEvent> {
    void processEvent(T event, Player player);

    EventType getEventType();
}
