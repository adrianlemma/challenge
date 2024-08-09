package com.geo.challenge.controller;

import com.geo.challenge.dto.request.TournamentGeneratorRequest;
import com.geo.challenge.dto.request.TournamentRandomGeneratorRequest;
import com.geo.challenge.dto.request.TournamentRequest;
import com.geo.challenge.dto.response.TournamentListResponse;
import com.geo.challenge.dto.response.TournamentResponse;
import com.geo.challenge.service.TournamentManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.geo.challenge.constant.ConstantValues.GEOPAGOS;

@RestController
@RequestMapping(GEOPAGOS)
@CrossOrigin(origins = "*")
public class TournamentController {

    private final TournamentManagementService tournamentManagementService;

    public TournamentController(TournamentManagementService tournamentManagementService) {
        this.tournamentManagementService = tournamentManagementService;
    }

    @PostMapping("/tournament/random")
    public ResponseEntity<TournamentResponse> generateRandomTournament(@RequestBody TournamentRandomGeneratorRequest request) {
        TournamentResponse tournament = tournamentManagementService.generateRandomTournament(request);
        return ResponseEntity.ok(tournament);
    }

    @PostMapping("/tournament")
    public ResponseEntity<TournamentResponse> generateTournament(@RequestBody TournamentGeneratorRequest request) {
        TournamentResponse tournament = tournamentManagementService.generateTournament(request);
        return ResponseEntity.ok(tournament);
    }

    @PostMapping("/tournament/query")
    public ResponseEntity<TournamentResponse> getTournament(@RequestBody TournamentRequest request) {
        TournamentResponse tournament = tournamentManagementService.getTournament(request);
        return ResponseEntity.ok(tournament);
    }

    @GetMapping("/tournament/list")
    public ResponseEntity<TournamentListResponse> getTournamentList() {
        TournamentListResponse tournamentList = new TournamentListResponse(tournamentManagementService.getTournamentList());
        return ResponseEntity.ok(tournamentList);
    }
}
