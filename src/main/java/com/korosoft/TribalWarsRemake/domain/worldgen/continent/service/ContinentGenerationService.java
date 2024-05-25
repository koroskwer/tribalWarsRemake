package com.korosoft.TribalWarsRemake.domain.worldgen.continent.service;

import com.korosoft.TribalWarsRemake.domain.worldgen.continent.Continent;

public interface ContinentGenerationService {

    Continent generateContinent(int continentNumber);
}
