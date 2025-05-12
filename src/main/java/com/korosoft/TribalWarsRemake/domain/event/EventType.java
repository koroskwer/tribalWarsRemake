package com.korosoft.TribalWarsRemake.domain.event;

import lombok.Getter;

enum EventType {
    ATTACK("attack"), SUPPORT("support"), TRANSPORT("transport"), PRODUCTION("production");

    @Getter
    private final String key;

    EventType(String key) {
        this.key = key;
    }
}
