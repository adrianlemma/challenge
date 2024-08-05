package com.geo.challenge.controller;

import com.geo.challenge.dto.request.PlayerRequest;
import com.geo.challenge.dto.response.PlayerResponse;
import com.geo.challenge.service.PlayerManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.geo.challenge.constant.ConstantValues.GEOPAGOS;

@RestController
@RequestMapping(GEOPAGOS)
@CrossOrigin(origins = "*")
public class PlayerController {

    private final PlayerManagementService playerService;

    public PlayerController(PlayerManagementService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/player/{player-id}")
    public ResponseEntity<PlayerResponse> getPlayer(@PathVariable("player-id") Integer playerId) {
        PlayerResponse response = playerService.getPlayer(playerId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/player")
    public ResponseEntity<PlayerResponse> listPlayers() {
        PlayerResponse response = playerService.getAllPlayers();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/player")
    public ResponseEntity<PlayerResponse> addPlayer(@RequestBody PlayerRequest request) {
        PlayerResponse response = playerService.createPlayer(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/player/{player-id}")
    public ResponseEntity<PlayerResponse> updatePlayer(@RequestBody PlayerRequest request, @PathVariable("player-id") Integer playerId) {
        PlayerResponse response = playerService.updatePlayer(request, playerId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/player/{player-id}")
    public ResponseEntity<PlayerResponse> deletePlayer(@PathVariable("player-id") Integer playerId) {
        playerService.deletePlayer(playerId);
        return ResponseEntity.ok().build();
    }
}
