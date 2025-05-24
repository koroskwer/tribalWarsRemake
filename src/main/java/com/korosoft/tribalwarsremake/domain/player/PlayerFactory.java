package com.korosoft.tribalwarsremake.domain.player;

import com.korosoft.tribalwarsremake.domain.player.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlayerFactory {

    private final PlayerRepository playerRepository;

    public Player createPlayer(String username) {
        Player player = new Player(username);
        this.playerRepository.save(player);
        return player;
    }
}
