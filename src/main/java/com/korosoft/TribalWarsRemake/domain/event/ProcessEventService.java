package com.korosoft.TribalWarsRemake.domain.event;

interface ProcessEventService {
    void processEvent(AbstractEvent event);
}
