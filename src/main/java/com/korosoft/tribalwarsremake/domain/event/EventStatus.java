package com.korosoft.tribalwarsremake.domain.event;

import lombok.Getter;

public enum EventStatus {
    ARCHIVED("archived"), READY("ready");

    @Getter
    private final String key;

    EventStatus(String key) {
        this.key = key;
    }
}
