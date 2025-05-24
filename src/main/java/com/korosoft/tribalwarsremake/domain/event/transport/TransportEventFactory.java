package com.korosoft.tribalwarsremake.domain.event.transport;

import com.korosoft.tribalwarsremake.domain.event.dto.TransportEventDto;
import com.korosoft.tribalwarsremake.domain.player.Player;
import com.korosoft.tribalwarsremake.domain.player.repository.PlayerRepository;
import com.korosoft.tribalwarsremake.domain.resources.Resources;
import com.korosoft.tribalwarsremake.domain.resources.ResourcesDto;
import com.korosoft.tribalwarsremake.domain.resources.ResourcesFacade;
import com.korosoft.tribalwarsremake.domain.village.Village;
import com.korosoft.tribalwarsremake.domain.village.VillageFacade;
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
