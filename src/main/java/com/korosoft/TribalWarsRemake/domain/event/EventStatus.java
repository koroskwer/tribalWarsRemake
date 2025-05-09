package com.korosoft.TribalWarsRemake.domain.event;

enum EventStatus {
    ARCHIVED("archived"), READY("ready");

    private final String key;

    EventStatus(String key) {
        this.key = key;
    }
}
