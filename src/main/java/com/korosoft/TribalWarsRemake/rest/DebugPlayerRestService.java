package com.korosoft.TribalWarsRemake.rest;

import com.korosoft.TribalWarsRemake.domain.player.Player;
import com.korosoft.TribalWarsRemake.domain.player.PlayerGenerationService;
import com.korosoft.TribalWarsRemake.domain.player.dto.PlayerGenerationDto;
import com.korosoft.TribalWarsRemake.domain.village.Village;
import com.korosoft.TribalWarsRemake.domain.village.VillageGenerationService;
import com.korosoft.TribalWarsRemake.domain.worldgen.WorldGenDirection;
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
    public ResponseEntity<String> generateMultipleAccounts(@PathVariable int amount, @RequestBody(required = false) WorldGenDirection direction) {
        List<Player> players = this.playerGenerationService.bulkGeneratePlayers(amount);
        List<Village> villages = this.villageGenerationService.bulkGenerateVillages(players, direction);
        return new ResponseEntity<>("TODO", HttpStatus.OK);
    }
}
