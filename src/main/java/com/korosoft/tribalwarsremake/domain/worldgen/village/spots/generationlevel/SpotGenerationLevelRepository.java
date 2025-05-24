package com.korosoft.tribalwarsremake.domain.worldgen.village.spots.generationlevel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotGenerationLevelRepository extends JpaRepository<SpotGenerationLevel, Integer> {
    SpotGenerationLevel findByDirection(String direction);
}
