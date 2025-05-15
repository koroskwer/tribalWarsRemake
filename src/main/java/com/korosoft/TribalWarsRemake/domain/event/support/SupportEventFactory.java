package com.korosoft.TribalWarsRemake.domain.event.support;

import org.springframework.stereotype.Component;

@Component
public class SupportEventFactory {
    SupportEvent createSupportEvent(){
        return new SupportEvent();
    }
}
