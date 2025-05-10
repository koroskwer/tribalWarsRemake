package com.korosoft.TribalWarsRemake.domain.event;


import com.korosoft.TribalWarsRemake.domain.event.dto.AttackEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.SupportEventDto;
import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;
import com.korosoft.TribalWarsRemake.domain.root.AbstractQueryServiceRoot;
import com.korosoft.TribalWarsRemake.domain.village.Village;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
class EventQueryServiceImpl extends AbstractQueryServiceRoot implements EventQueryService {

    private final Clock clock;
    private final AttackEventRepository attackEventRepository;

    @Override
    public List<AbstractEvent> getAllEventsToProcess(int playerId) {
        List<AbstractEvent> list = new ArrayList<>();
        list.addAll(this.getAttackEvents(playerId));
        list.addAll(this.getSupportEvents(playerId));
        list.addAll(this.getTransportEvents(playerId));
        return list;
    }

    @Override
    public List<TransportEvent> getTransportEvents(int playerId) {
        return List.of();
    }

    @Override
    public List<AttackEvent> getAttackEvents(int playerId) {
        return List.of();
    }

    @Override
    public List<SupportEvent> getSupportEvents(int playerId) {
        return List.of();
    }

    @Override
    public void addAttackEvent(AttackEventDto attackEventDto) {
        Instant currentTime = this.clock.instant();
        Village targetVillage = this.entityManager.getReference(Village.class, attackEventDto.targetVillageId());
        Village sourceVillage = this.entityManager.getReference(Village.class, attackEventDto.sourceVillageId());
        AttackEvent event = new AttackEvent(attackEventDto, currentTime, currentTime, targetVillage, sourceVillage);
        this.attackEventRepository.save(event);
    }

    @Override
    public void addSupportEvent(SupportEventDto supportEventDto) {
        Instant currentTime = this.clock.instant();
    }

    @Override
    public void addTransportEvent(TransportEventDto transportEventDto) {
        Instant currentTime = this.clock.instant();
    }
}
