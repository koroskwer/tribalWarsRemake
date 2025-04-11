package com.korosoft.TribalWarsRemake.domain.village;

import com.korosoft.TribalWarsRemake.config.GameConstants;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.village.repository.VillageRepository;
import com.korosoft.TribalWarsRemake.domain.worldgen.village.spots.Spot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VillageFactory {

    private final VillageRepository villageRepository;

    public Village createVillage(Spot spot, Player owner) {
        return this.createVillage(spot, owner, String.format(GameConstants.DEFAULT_VILLAGE_NAME, owner.getName()));
    }

    public Village createVillage(Spot spot, Player owner, String name) {
        Village village = new Village(spot, owner, name);
        this.villageRepository.save(village);
        return village;
    }
}
