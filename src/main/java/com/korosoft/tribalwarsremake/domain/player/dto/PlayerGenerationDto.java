package com.korosoft.tribalwarsremake.domain.player.dto;

import com.korosoft.tribalwarsremake.domain.worldgen.WorldGenDirection;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class PlayerGenerationDto implements Serializable {

    private String playerName;

    private WorldGenDirection direction;
}
