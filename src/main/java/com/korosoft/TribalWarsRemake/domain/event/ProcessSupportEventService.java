package com.korosoft.TribalWarsRemake.domain.event;

import org.springframework.stereotype.Component;

@Component
class ProcessSupportEventService implements ProcessEventService {
    @Override
    public void processEvent(AbstractEvent event) {
        //TODO support event handling
    }
}
