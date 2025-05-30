package com.korosoft.tribalwarsremake.domain.event;

import lombok.Getter;

public enum EventType {
    ATTACK("attack"), SUPPORT("support"), TRANSPORT("transport"), PRODUCTION("production");

    @Getter
    private final String key;

    EventType(String key) {
        this.key = key;
    }
}
