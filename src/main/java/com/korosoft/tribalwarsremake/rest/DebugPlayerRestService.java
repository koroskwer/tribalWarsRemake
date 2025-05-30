package com.korosoft.tribalwarsremake.rest;

import com.korosoft.tribalwarsremake.domain.player.Player;
import com.korosoft.tribalwarsremake.domain.player.PlayerGenerationService;
import com.korosoft.tribalwarsremake.domain.player.dto.PlayerGenerationDto;
import com.korosoft.tribalwarsremake.domain.village.Village;
import com.korosoft.tribalwarsremake.domain.village.VillageGenerationService;
import com.korosoft.tribalwarsremake.domain.worldgen.WorldGenDirection;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/debugPlayer")
@AllArgsConstructor
public class DebugPlayerRestService {

    private PlayerGenerationService playerGenerationService;
    private VillageGenerationService villageGenerationService;

    @PostMapping("/generate/single")
    public ResponseEntity<String> generateSingleAccount(@RequestBody PlayerGenerationDto playerGenerationDto) {
        Player player = this.playerGenerationService.generatePlayer(playerGenerationDto.getPlayerName());
        Village village = this.villageGenerationService.generateVillage(player, playerGenerationDto.getDirection());
        return new ResponseEntity<>(village.getXCoord() + " " + village.getYCoord(), HttpStatus.OK);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @PostMapping("/generate/multiple/{amount}")
    public ResponseEntity<List<Village>> generateMultipleAccounts(@PathVariable int amount, @RequestBody(required = false) WorldGenDirection direction) {
        List<Player> players = this.playerGenerationService.bulkGeneratePlayers(amount);
        List<Village> villages = this.villageGenerationService.bulkGenerateVillages(players, direction);
        return new ResponseEntity<>(villages, HttpStatus.OK);
    }
}
