package com.korosoft.TribalWarsRemake.domain.village;

import com.korosoft.TribalWarsRemake.config.GameConstants;
import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.resources.ResourcesFacade;
import com.korosoft.TribalWarsRemake.domain.village.repository.VillageRepository;
import com.korosoft.TribalWarsRemake.domain.worldgen.village.spots.Spot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class VillageFactory {

    private final VillageRepository villageRepository;
    private final ResourcesFacade resourcesFacade;

    public Village createVillage(Spot spot, Player owner) {
        return this.createVillage(spot, owner, String.format(GameConstants.DEFAULT_VILLAGE_NAME, owner.getName()));
    }

    public Village createVillage(Spot spot, Player owner, String name) {
        Village village = new Village(spot, owner, name, this.resourcesFacade.createResources());
        this.villageRepository.save(village);
        return village;
    }

    /**
     * Size of Spots and Players must be the same
     *
     * @param spots
     * @param players
     * @return
     */
    public List<Village> bulkCreateVillage(List<Spot> spots, List<Player> players) {
        if (spots.size() != players.size()) {
            throw new IllegalArgumentException("Amount of Players must be same as amount of spots");
        }
        List<Village> villages = new ArrayList<>();
        for (int i = 0; i < spots.size(); i++) {
            Player owner = players.get(i);
            villages.add(new Village(spots.get(i), owner, String.format(GameConstants.DEFAULT_VILLAGE_NAME, owner.getName()), this.resourcesFacade.createResources()));
        }
        this.villageRepository.saveAll(villages);
        return villages;
    }
}
