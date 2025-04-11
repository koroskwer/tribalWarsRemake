package com.korosoft.TribalWarsRemake.domain.player.repository;


import com.korosoft.TribalWarsRemake.domain.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
