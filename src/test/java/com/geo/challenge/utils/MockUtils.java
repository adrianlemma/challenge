package com.geo.challenge.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geo.challenge.dto.FemalePlayer;
import com.geo.challenge.dto.MalePlayer;
import com.geo.challenge.dto.request.PlayerRequest;
import com.geo.challenge.dto.request.TournamentGeneratorRequest;
import com.geo.challenge.dto.request.TournamentRandomGeneratorRequest;
import com.geo.challenge.dto.request.TournamentRequest;
import com.geo.challenge.dto.response.PlayerResponse;
import com.geo.challenge.dto.response.TournamentGameResponse;
import com.geo.challenge.dto.response.TournamentPhaseResponse;
import com.geo.challenge.dto.response.TournamentResponse;
import com.geo.challenge.model.Player;
import com.geo.challenge.model.Tournament;
import com.geo.challenge.model.TournamentPhase;
import com.geo.challenge.model.TournamentPhaseGame;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.geo.challenge.constant.ConstantValues.FEMALE;
import static com.geo.challenge.constant.ConstantValues.MALE;

public final class MockUtils {

    public static Tournament mockTournament() {
        Tournament tournament = new Tournament();
        tournament.setTournamentId(1);
        tournament.setName("tournament_name");
        tournament.setDate(Date.valueOf(LocalDate.now()));
        tournament.setCompetitors(4);
        tournament.setType("male");
        tournament.setPhases(mockPhaseList());
        tournament.getPhases().forEach(phase -> phase.setTournament(tournament));
        tournament.setWinner(mockPlayer(MALE, 1));
        return tournament;
    }

    public static Player mockPlayer(String gender, Integer id) {
        if (gender.equals(MALE))
            return new Player(id, "jugador" + id, 100, null, 100, 50.0, gender);
        else
            return new Player(id, "jugadora" + id, 100, 0.01, null, null, gender);
    }

    public static List<TournamentPhase> mockPhaseList() {
        List<TournamentPhase> phaseList = new ArrayList<>();
        phaseList.add(new TournamentPhase());
        phaseList.add(new TournamentPhase());
        phaseList.get(0).setPhaseNumber(0);
        phaseList.get(0).setPhaseId(10);
        phaseList.get(0).setGames(new ArrayList<>());
        phaseList.get(0).getGames().add(
                new TournamentPhaseGame(100, mockPlayer(MALE, 1), mockPlayer(MALE, 2), phaseList.get(0)));
        phaseList.get(0).getGames().add(
                new TournamentPhaseGame(101, mockPlayer(MALE, 3), mockPlayer(MALE, 4), phaseList.get(0)));
        phaseList.get(1).setPhaseNumber(1);
        phaseList.get(1).setPhaseId(11);
        phaseList.get(1).setGames(new ArrayList<>());
        phaseList.get(1).getGames().add(
                new TournamentPhaseGame(102, mockPlayer(MALE, 5), mockPlayer(MALE, 6), phaseList.get(1)));
        return phaseList;
    }

    public static MalePlayer mockMalePlayer() {
        return new MalePlayer(1, "player1", 100, 100, 50.0);
    }

    public static FemalePlayer mockFemalePlayer() {
        return new FemalePlayer(1, "player1", 100, 0.01);
    }

    public static PlayerRequest mockPlayerRequest(String gender) {
        if (gender.equals(MALE))
            return new PlayerRequest("player1", 100, MALE, 100, 50.0, null);
        else
            return new PlayerRequest("player1", 100, FEMALE, null, null, 0.1);
    }

    public static PlayerResponse mockPlayerResponse(String gender) {
        PlayerResponse response = new PlayerResponse(new ArrayList<>());
        response.getData().add(gender.equals(MALE) ? mockMalePlayer() : mockFemalePlayer());
        return response;
    }

    public static TournamentRequest mockTournamentRequest() {
        return new TournamentRequest("tournament_name", MALE, "31/12/2023");
    }

    public static TournamentResponse mockTournamentResponse() {
        TournamentResponse response = new TournamentResponse();
        response.setName("tournament_name");
        response.setType(MALE);
        response.setDate("31/12/2023");
        response.setCompetitors(4);
        response.setWinner(mockMalePlayer());
        response.getPhases().add(new TournamentPhaseResponse());
        response.getPhases().add(new TournamentPhaseResponse());
        response.getPhases().get(0).setPhaseNumber(0);
        response.getPhases().get(0).setPhaseGames(new ArrayList<>());
        response.getPhases().get(0).getPhaseGames().add(new TournamentGameResponse());
        response.getPhases().get(0).getPhaseGames().add(new TournamentGameResponse());
        response.getPhases().get(0).getPhaseGames().get(0).setGameId(1);
        response.getPhases().get(0).getPhaseGames().get(0).setWinner("player1");
        response.getPhases().get(0).getPhaseGames().get(0).setLoser("player2");
        response.getPhases().get(0).getPhaseGames().get(1).setGameId(2);
        response.getPhases().get(0).getPhaseGames().get(1).setWinner("player3");
        response.getPhases().get(0).getPhaseGames().get(1).setLoser("player4");
        response.getPhases().get(1).setPhaseNumber(1);
        response.getPhases().get(1).setPhaseGames(new ArrayList<>());
        response.getPhases().get(1).getPhaseGames().add(new TournamentGameResponse());
        response.getPhases().get(1).getPhaseGames().get(0).setGameId(1);
        response.getPhases().get(1).getPhaseGames().get(0).setWinner("player1");
        response.getPhases().get(1).getPhaseGames().get(0).setLoser("player4");
        return response;
    }

    public static TournamentGeneratorRequest mockTournamentGeneratorRequest() {
        return new TournamentGeneratorRequest("tournament_name", MALE, "31/12/2023", List.of(1, 2, 3, 4));
    }

    public static TournamentRandomGeneratorRequest mockTournamentRandomGeneratorRequest() {
        return new TournamentRandomGeneratorRequest("tournament_name", MALE, "31/12/2023", 4);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
