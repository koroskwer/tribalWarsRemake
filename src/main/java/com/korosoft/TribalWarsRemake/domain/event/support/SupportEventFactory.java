package com.korosoft.TribalWarsRemake.domain.event.support;

import org.springframework.stereotype.Component;

@Component
class SupportEventFactory {
    SupportEvent createSupportEvent(){
        return new SupportEvent();
    }
}
