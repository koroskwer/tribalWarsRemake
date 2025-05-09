package com.korosoft.TribalWarsRemake.domain.event;

import org.springframework.stereotype.Component;

@Component
class ProcessTransportEventService implements ProcessEventService {
    @Override
    public void processEvent(AbstractEvent event) {
        //TODO transport event handling here
    }
}
