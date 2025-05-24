package com.korosoft.TribalWarsRemake.domain.event.transport;

import com.korosoft.TribalWarsRemake.domain.event.dto.TransportEventDto;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.player.repository.PlayerRepository;
import com.korosoft.TribalWarsRemake.domain.resources.Resources;
import com.korosoft.TribalWarsRemake.domain.resources.ResourcesDto;
import com.korosoft.TribalWarsRemake.domain.resources.ResourcesFacade;
import com.korosoft.TribalWarsRemake.domain.village.Village;
import com.korosoft.TribalWarsRemake.domain.village.VillageFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
class TransportEventFactory {

    private final ResourcesFacade resourcesFacade;
    private final VillageFacade villageFacade;
    private final PlayerRepository playerRepository;

    TransportEvent createTransportEvent() {
        return new TransportEvent();
    }

    TransportEvent createTransportEvent(TransportEventDto transportEventDto) {
        Village sourceVillage = this.villageFacade.getVillageReference(transportEventDto.getSourceVillageId());
        Village targetVillage = this.villageFacade.getVillageReference(transportEventDto.getTargetVillageId());
        List<Player> playerList = this.playerRepository.findAllById(transportEventDto.getPlayerIds());
        ResourcesDto resourcesDto = transportEventDto.getResourcesDto();
        Resources resources = this.resourcesFacade.splitResources(sourceVillage.getResources(), resourcesDto);
        return new TransportEvent(transportEventDto.getEventStatus(), transportEventDto.getEventType(), transportEventDto.getStartTime(),
                transportEventDto.getEndTime(), playerList, resources, targetVillage, sourceVillage);
    }
}
