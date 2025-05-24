package com.korosoft.tribalwarsremake.domain.time;

import com.korosoft.tribalwarsremake.domain.event.EventType;
import org.springframework.stereotype.Component;

import java.time.Instant;

//TODO add strategies of travel time for different event types
@Component
public class FinishTimeFacade {

    public Instant getTravelTime(Instant start, EventType type) {
        return start;
    }
}
