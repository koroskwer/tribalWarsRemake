package com.korosoft.tribalwarsremake.domain.player.repository;


import com.korosoft.tribalwarsremake.domain.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
