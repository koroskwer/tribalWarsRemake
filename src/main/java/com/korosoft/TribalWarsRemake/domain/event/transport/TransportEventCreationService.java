package com.korosoft.TribalWarsRemake.domain.event.transport;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEventCreationService;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;
import com.korosoft.TribalWarsRemake.domain.root.AbstractQueryServiceRoot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor
class TransportEventCreationService extends AbstractQueryServiceRoot implements AbstractEventCreationService<TransportEventDto> {

    private final TransportEventRepository transportEventRepository;
    private final TransportEventFactory transportEventFactory;

    @Override
    public void addEvent(TransportEventDto eventDto, Instant timestamp) {
        this.transportEventRepository.save(this.transportEventFactory.createTransportEvent(eventDto));
    }
}
