package com.korosoft.TribalWarsRemake.domain.event;

enum EventType {
    ATTACK("attack"), SUPPORT("support"), TRANSPORT("transport");

    private final String key;

    EventType(String key) {
        this.key = key;
    }
}
