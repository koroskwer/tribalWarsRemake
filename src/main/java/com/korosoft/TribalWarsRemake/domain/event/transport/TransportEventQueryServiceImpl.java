package com.korosoft.TribalWarsRemake.domain.event.transport;

import com.korosoft.TribalWarsRemake.domain.event.AbstractEvent;
import com.korosoft.TribalWarsRemake.domain.event.EventStatus;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;
import com.korosoft.TribalWarsRemake.domain.root.AbstractQueryServiceRoot;
import lombok.AllArgsConstructor;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@AllArgsConstructor
class TransportEventQueryServiceImpl extends AbstractQueryServiceRoot implements TransportEventQueryService {

    private static final QTransportEvent Q_TRANSPORT_EVENT = QTransportEvent.transportEvent;
    private final TransportEventRepository transportEventRepository;

    @Override
    public List<AbstractEvent> getTransportEvents(int playerId, Instant timestamp) {
        return entityManager.createQuery("""
                        select p
                        from TransportEvent p
                        where p.eventRoot.eventStatus = :status and p.eventRoot.finishDate < :timestamp
                        order by p.id
                        """, TransportEvent.class)
                .setParameter("status", EventStatus.READY)
                .setParameter("timestamp", timestamp)
                .unwrap(Query.class)
                .setHibernateLockMode(LockMode.UPGRADE_SKIPLOCKED).getResultList();
    }

    @Override
    public void addTransportEvent(TransportEventDto transportEventDto, Instant timestamp) {
        this.transportEventRepository.save(new TransportEvent(timestamp, timestamp));
    }

    @Override
    public void removeTransportEvents(int playerId, Instant timestamp) {

    }
}
