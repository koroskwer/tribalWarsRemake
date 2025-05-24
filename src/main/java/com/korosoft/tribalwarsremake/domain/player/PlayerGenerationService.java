package com.korosoft.tribalwarsremake.domain.player;

import java.util.List;

public interface PlayerGenerationService {
    Player generatePlayer(String playerName);

    List<Player> bulkGeneratePlayers(int amount);
}
