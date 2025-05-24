package com.korosoft.tribalwarsremake.domain.village.impl;

import com.korosoft.tribalwarsremake.domain.player.Player;
import com.korosoft.tribalwarsremake.domain.utils.RandomEnumGetter;
import com.korosoft.tribalwarsremake.domain.village.Village;
import com.korosoft.tribalwarsremake.domain.village.VillageFactory;
import com.korosoft.tribalwarsremake.domain.village.VillageGenerationService;
import com.korosoft.tribalwarsremake.domain.worldgen.WorldGenDirection;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.Spot;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.SpotRepository;
import com.korosoft.tribalwarsremake.domain.worldgen.village.spots.SpotRetrievalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class VillageGenerationServiceImpl implements VillageGenerationService {

    private final SpotRetrievalService spotRetrievalService;
    private final SpotRepository spotRepository;
    private final VillageFactory villageFactory;

    /**
     * Generates a village in a random direction
     */
    @Override
    public Village generateVillage(Player player) {
        return this.generateVillage(player, RandomEnumGetter.randomEnum(WorldGenDirection.class));
    }

    /**
     * Generates a village in a provided direction
     */
    @Override
    public Village generateVillage(Player player, WorldGenDirection direction) {
        Spot spot = this.spotRetrievalService.getRandomSpot(direction);
        Village village = this.villageFactory.createVillage(spot, player);
        this.spotRepository.delete(spot);
        return village;
    }

    @Override
    public List<Village> bulkGenerateVillages(List<Player> players, WorldGenDirection direction) {
        List<Village> villages = new ArrayList<>();

        int iterator = 0;
        int paginationSize = 20;

        while (players.size() > iterator) {
            int actualAmountToAdd = Math.min(paginationSize, players.size() - iterator);
            List<Spot> spots = this.spotRetrievalService.getRandomSpots(direction, actualAmountToAdd);
            List<Player> playersSublist = players.subList(iterator, iterator + actualAmountToAdd);
            villages.addAll(this.villageFactory.bulkCreateVillage(spots, playersSublist));
            this.spotRepository.deleteAll(spots);
            iterator += paginationSize;
        }
        return villages;
    }

    @Override
    public List<Village> bulkGenerateVillages(List<Player> players) {
        List<Village> villages = new ArrayList<>();

        for (Player player : players) {
            Spot spot = this.spotRetrievalService.getRandomSpot(RandomEnumGetter.randomEnum(WorldGenDirection.class));
            villages.add(this.villageFactory.createVillage(spot, player));
            this.spotRepository.delete(spot);
        }
        return villages;
    }
}
