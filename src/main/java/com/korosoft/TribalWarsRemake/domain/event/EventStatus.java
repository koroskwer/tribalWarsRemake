package com.korosoft.TribalWarsRemake.domain.event;

import lombok.Getter;

enum EventStatus {
    ARCHIVED("archived"), READY("ready");

    @Getter
    private final String key;

    EventStatus(String key) {
        this.key = key;
    }
}
