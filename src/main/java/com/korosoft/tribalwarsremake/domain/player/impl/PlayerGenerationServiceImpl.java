package com.korosoft.tribalwarsremake.domain.player.impl;

import com.korosoft.tribalwarsremake.domain.player.Player;
import com.korosoft.tribalwarsremake.domain.player.PlayerFactory;
import com.korosoft.tribalwarsremake.domain.player.PlayerGenerationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PlayerGenerationServiceImpl implements PlayerGenerationService {

    private PlayerFactory playerFactory;

    @Override
    public Player generatePlayer(String playerName) {
        return this.playerFactory.createPlayer(playerName);
    }

    @Override
    public List<Player> bulkGeneratePlayers(int amount) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            players.add(this.playerFactory.createPlayer(UUID.randomUUID().toString()));
        }
        return players;
    }
}
