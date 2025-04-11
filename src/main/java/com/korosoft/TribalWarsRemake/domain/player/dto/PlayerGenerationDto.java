package com.korosoft.TribalWarsRemake.domain.player.dto;

import com.korosoft.TribalWarsRemake.domain.worldgen.WorldGenDirection;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class PlayerGenerationDto implements Serializable {

    private String playerName;

    private WorldGenDirection direction;
}
