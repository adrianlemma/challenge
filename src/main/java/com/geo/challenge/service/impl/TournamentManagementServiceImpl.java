package com.geo.challenge.service.impl;

import com.geo.challenge.dto.request.TournamentGeneratorRequest;
import com.geo.challenge.dto.request.TournamentRandomGeneratorRequest;
import com.geo.challenge.dto.request.TournamentRequest;
import com.geo.challenge.dto.response.TournamentResponse;
import com.geo.challenge.exception.GenericException;
import com.geo.challenge.exception.TournamentException;
import com.geo.challenge.mapper.TournamentMapper;
import com.geo.challenge.model.Player;
import com.geo.challenge.model.Tournament;
import com.geo.challenge.model.TournamentPhase;
import com.geo.challenge.model.TournamentPhaseGame;
import com.geo.challenge.service.PlayerService;
import com.geo.challenge.service.RequestValidator;
import com.geo.challenge.service.TournamentManagementService;
import com.geo.challenge.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static com.geo.challenge.constant.ConstantValues.MALE;
import static com.geo.challenge.constant.ErrorCodes.*;

@Service
public class TournamentManagementServiceImpl implements TournamentManagementService {

    private final RequestValidator requestValidator;
    private final TournamentService tournamentService;
    private final TournamentMapper tournamentMapper;
    private final PlayerService playerService;

    private final Random random = new Random();

    public TournamentManagementServiceImpl(RequestValidator requestValidator, TournamentService tournamentService, TournamentMapper tournamentMapper, PlayerService playerService) {
        this.requestValidator = requestValidator;
        this.tournamentService = tournamentService;
        this.tournamentMapper = tournamentMapper;
        this.playerService = playerService;
    }

    @Override
    public TournamentResponse generateRandomTournament(TournamentRandomGeneratorRequest request) {
        requestValidator.validateTournamentRandomGeneratorRequest(request);
        if (tournamentService.existsByName(request.getName()))
            throw new TournamentException(TOURNAMENT_NAME_DUPLICATED.getCode(),
                    String.format(TOURNAMENT_NAME_DUPLICATED.getDescription(), request.getName()));
        if (playerService.countByGender(request.getType()) < request.getCompetitors())
            throw new TournamentException(TOURNAMENT_NOT_ENOUGH_DATA.getCode(), TOURNAMENT_NOT_ENOUGH_DATA.getDescription());
        Tournament response = new Tournament();
        response.setName(request.getName());
        response.setType(request.getType());
        response.setCompetitors(request.getCompetitors());
        String[] dateParts = request.getDate().split("/");
        response.setDate(Date.valueOf(LocalDate.of(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[0]))));
        List<Player> players = playerService.findRandomPlayers(request.getCompetitors(), request.getType());
        completePhases(response, players);
        Tournament savedTournament = tournamentService.save(response);
        return tournamentMapper.tournamentToTournamentResponse(savedTournament);
    }

    @Override
    public TournamentResponse generateTournament(TournamentGeneratorRequest request) {
        requestValidator.validateTournamentGeneratorRequest(request);
        if (tournamentService.existsByName(request.getName()))
            throw new TournamentException(TOURNAMENT_NAME_DUPLICATED.getCode(),
                    String.format(TOURNAMENT_NAME_DUPLICATED.getDescription(), request.getName()));
        Tournament response = new Tournament();
        response.setName(request.getName());
        response.setType(request.getType());
        response.setCompetitors(request.getPlayerIds().size());
        String[] dateParts = request.getDate().split("/");
        response.setDate(Date.valueOf(LocalDate.of(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[0]))));
        List<Player> players = playerService.findPlayersByIds(request.getPlayerIds(), request.getType());
        if (players.size() != request.getPlayerIds().size())
            throw new TournamentException(PARTIAL_PLAYER_DATA.getCode(), PARTIAL_PLAYER_DATA.getDescription());
        completePhases(response, players);
        Tournament savedTournament = tournamentService.save(response);
        return tournamentMapper.tournamentToTournamentResponse(savedTournament);
    }

    @Override
    public TournamentResponse getTournament(TournamentRequest request) {
        requestValidator.validateTournamentRequest(request);
        Tournament result;
        if (Objects.isNull(request.getName())) {
            if (Objects.isNull(request.getType()) && tournamentService.countByDate(request.getDate()) > 1)
                throw new TournamentException(MULTIPLE_RESULT_ERROR.getCode(), MULTIPLE_RESULT_ERROR.getDescription());
            else if (Objects.nonNull(request.getType()) && tournamentService.countByDateAndType(request.getDate(), request.getType()) > 1)
                throw new TournamentException(MULTIPLE_RESULT_ERROR.getCode(), MULTIPLE_RESULT_ERROR.getDescription());
            if (Objects.isNull(request.getType()))
                result = tournamentService.findByDate(request.getDate());
            else
                result = tournamentService.findByDateAndType(request.getDate(), request.getType());
        } else {
            result = tournamentService.findByName(request.getName());
        }
        if (Objects.isNull(result))
            throw new GenericException(HttpStatus.NOT_FOUND, TOURNAMENT_NOT_FOUND.getCode(), TOURNAMENT_NOT_FOUND.getDescription());
        return tournamentMapper.tournamentToTournamentResponse(result);
    }

    private void completePhases(Tournament tournament, List<Player> players) {
        Integer phaseCount = 0;
        while (Objects.isNull(tournament.getWinner())) {
            TournamentPhase phase = new TournamentPhase();
            Set<Integer> toRemove = new HashSet<>();
            phase.setPhaseNumber(phaseCount);
            phase.setTournament(tournament);
            for (int i = 0; i < players.size(); i += 2) {
                phase.addGame(getGameResult(players.get(i), players.get(i + 1), phase, toRemove));
            }
            tournament.addPhase(phase);
            phaseCount++;
            players.removeIf(player -> toRemove.contains(player.getPlayerId()));
            if (players.size() == 1)
                tournament.setWinner(players.get(0));
        }
    }

    private TournamentPhaseGame getGameResult(Player player1, Player player2, TournamentPhase phase, Set<Integer> toRemove) {
        Double points1 = getPoints(player1);
        Double points2 = getPoints(player2);
        TournamentPhaseGame gameRecord;
        try {
            if (points1 > points2) {
                gameRecord = new TournamentPhaseGame(null, (Player) player1.clone(), (Player) player2.clone(), phase);
                toRemove.add(player2.getPlayerId());
            } else if (points2 > points1) {
                gameRecord = new TournamentPhaseGame(null, (Player) player2.clone(), (Player) player1.clone(), phase);
                toRemove.add(player1.getPlayerId());
            } else {
                gameRecord = getGameResult(player1, player2, phase, toRemove);
            }
        } catch (CloneNotSupportedException ex) {
            throw new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, UNEXPECTED_ERROR.getCode(), UNEXPECTED_ERROR.getDescription());
        }
        return gameRecord;
    }

    private Double getPoints(Player player) {
        if (player.getGender().equalsIgnoreCase(MALE))
            return player.getSkill() + player.getSpeed() + player.getStrength() / 2 + random.nextInt(51);
        else
            return player.getSkill() + (1 - player.getReactionTime()) * 50 + random.nextInt(51);
    }
}
