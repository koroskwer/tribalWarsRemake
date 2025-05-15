package com.korosoft.TribalWarsRemake.domain.event.transport;

import org.springframework.stereotype.Component;

@Component
class TransportEventFactory {
    TransportEvent createTransportEvent(){
        return new TransportEvent();
    }
}
