package com.korosoft.TribalWarsRemake.domain.village;

import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.worldgen.WorldGenDirection;

import java.util.List;

public interface VillageGenerationService {
    Village generateVillage(Player player);

    Village generateVillage(Player player, WorldGenDirection direction);

    List<Village> bulkGenerateVillages(List<Player> players, WorldGenDirection direction);

    List<Village> bulkGenerateVillages(List<Player> players);
}
