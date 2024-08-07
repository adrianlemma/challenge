package com.geo.challenge.service;

import com.geo.challenge.dto.request.PlayerRequest;
import com.geo.challenge.dto.request.TournamentGeneratorRequest;
import com.geo.challenge.dto.request.TournamentRandomGeneratorRequest;
import com.geo.challenge.dto.request.TournamentRequest;
import com.geo.challenge.exception.PlayerException;
import com.geo.challenge.exception.TournamentException;
import com.geo.challenge.service.impl.RequestValidatorImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.geo.challenge.constant.ConstantValues.FEMALE;
import static com.geo.challenge.constant.ConstantValues.MALE;
import static com.geo.challenge.utils.MockUtils.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RequestValidatorTest {

    @InjectMocks
    private RequestValidatorImpl validator;

    @Test
    @DisplayName("valid-player-male-request")
    void testValidPlayerMaleRequest() {
        validator.validatePlayerRequest(mockPlayerRequest(MALE));
    }

    @Test
    @DisplayName("valid-player-female-request")
    void testValidPlayerFemaleRequest() {
        validator.validatePlayerRequest(mockPlayerRequest(FEMALE));
    }

    @Test
    @DisplayName("invalid-player-name")
    void testInvalidPlayerName() {
        PlayerRequest playerRequest = mockPlayerRequest(MALE);
        playerRequest.setName(" ");
        assertThrows(PlayerException.class, () -> validator.validatePlayerRequest(playerRequest));
    }

    @Test
    @DisplayName("invalid-player-skill")
    void testInvalidPlayerSkill() {
        PlayerRequest playerRequest = mockPlayerRequest(MALE);
        playerRequest.setSkill(101);
        assertThrows(PlayerException.class, () -> validator.validatePlayerRequest(playerRequest));
    }

    @Test
    @DisplayName("invalid-player-gender")
    void testInvalidPlayerGender() {
        PlayerRequest playerRequest = mockPlayerRequest(MALE);
        playerRequest.setGender("ERROR_GENDER");
        assertThrows(PlayerException.class, () -> validator.validatePlayerRequest(playerRequest));
    }

    @Test
    @DisplayName("invalid-player-strength")
    void testInvalidPlayerStrength() {
        PlayerRequest playerRequest = mockPlayerRequest(MALE);
        playerRequest.setStrength(101);
        assertThrows(PlayerException.class, () -> validator.validatePlayerRequest(playerRequest));
    }

    @Test
    @DisplayName("invalid-player-speed")
    void testInvalidPlayerSpeed() {
        PlayerRequest playerRequest = mockPlayerRequest(MALE);
        playerRequest.setSpeed(51.0);
        assertThrows(PlayerException.class, () -> validator.validatePlayerRequest(playerRequest));
    }

    @Test
    @DisplayName("invalid-player-reaction-time")
    void testInvalidPlayerReactionTime() {
        PlayerRequest playerRequest = mockPlayerRequest(FEMALE);
        playerRequest.setReactionTime(3.0);
        assertThrows(PlayerException.class, () -> validator.validatePlayerRequest(playerRequest));
    }

    @Test
    @DisplayName("valid-tournament-request")
    void testValidTournamentRequest() {
        validator.validateTournamentRequest(mockTournamentRequest());
    }

    @Test
    @DisplayName("invalid-tournament-name")
    void testInvalidTournamentName() {
        TournamentRequest tournamentRequest = mockTournamentRequest();
        tournamentRequest.setName(" ");
        assertThrows(TournamentException.class, () -> validator.validateTournamentRequest(tournamentRequest));
    }

    @Test
    @DisplayName("invalid-tournament-date")
    void testInvalidTournamentDate() {
        TournamentRequest tournamentRequest = mockTournamentRequest();
        tournamentRequest.setDate("32/12/2023");
        assertThrows(TournamentException.class, () -> validator.validateTournamentRequest(tournamentRequest));
    }

    @Test
    @DisplayName("invalid-tournament-type")
    void testInvalidTournamentType() {
        TournamentRequest tournamentRequest = mockTournamentRequest();
        tournamentRequest.setType("INVALID_TYPE");
        assertThrows(TournamentException.class, () -> validator.validateTournamentRequest(tournamentRequest));
    }

    @Test
    @DisplayName("invalid-tournament-not-enough-params")
    void testInvalidTournamentNotEnoughParams() {
        TournamentRequest tournamentRequest = mockTournamentRequest();
        tournamentRequest.setName(null);
        tournamentRequest.setDate(null);
        tournamentRequest.setType(null);
        assertThrows(TournamentException.class, () -> validator.validateTournamentRequest(tournamentRequest));
    }

    @Test
    @DisplayName("valid-tournament-generator-request")
    void testValidTournamentGeneratorRequest() {
        validator.validateTournamentGeneratorRequest(mockTournamentGeneratorRequest());
    }

    @Test
    @DisplayName("invalid-tournament-generator-name")
    void testInvalidTournamentGeneratorName() {
        TournamentGeneratorRequest tournamentRequest = mockTournamentGeneratorRequest();
        tournamentRequest.setName(" ");
        assertThrows(TournamentException.class, () -> validator.validateTournamentGeneratorRequest(tournamentRequest));
    }

    @Test
    @DisplayName("invalid-tournament-generator-date")
    void testInvalidTournamentGeneratorDate() {
        TournamentGeneratorRequest tournamentRequest = mockTournamentGeneratorRequest();
        tournamentRequest.setDate("32/12/2023");
        assertThrows(TournamentException.class, () -> validator.validateTournamentGeneratorRequest(tournamentRequest));
    }

    @Test
    @DisplayName("invalid-tournament-generator-type")
    void testInvalidTournamentGeneratorType() {
        TournamentGeneratorRequest tournamentRequest = mockTournamentGeneratorRequest();
        tournamentRequest.setType("INVALID_TYPE");
        assertThrows(TournamentException.class, () -> validator.validateTournamentGeneratorRequest(tournamentRequest));
    }

    @Test
    @DisplayName("invalid-tournament-generator-empty-id-list")
    void testInvalidTournamentGeneratorEmptyIdList() {
        TournamentGeneratorRequest tournamentRequest = mockTournamentGeneratorRequest();
        tournamentRequest.setPlayerIds(new ArrayList<>());
        assertThrows(TournamentException.class, () -> validator.validateTournamentGeneratorRequest(tournamentRequest));
    }

    @Test
    @DisplayName("invalid-tournament-generator-invalid-id-list")
    void testInvalidTournamentGeneratorInvalidIdList() {
        TournamentGeneratorRequest tournamentRequest = mockTournamentGeneratorRequest();
        tournamentRequest.setPlayerIds(List.of(1, 2, 3));
        assertThrows(TournamentException.class, () -> validator.validateTournamentGeneratorRequest(tournamentRequest));
    }

    @Test
    @DisplayName("invalid-tournament-generator-invalid-date")
    void testInvalidTournamentGeneratorInvalidDate() {
        TournamentGeneratorRequest tournamentRequest = mockTournamentGeneratorRequest();
        tournamentRequest.setDate("123");
        assertThrows(TournamentException.class, () -> validator.validateTournamentGeneratorRequest(tournamentRequest));
    }

    @Test
    @DisplayName("valid-tournament-random-request")
    void testValidTournamentRandomRequest() {
        validator.validateTournamentRandomGeneratorRequest(mockTournamentRandomGeneratorRequest());
    }

    @Test
    @DisplayName("invalid-tournament-random-invalid-quantity")
    void testInvalidTournamentGeneratorInvalidQuantity() {
        TournamentRandomGeneratorRequest tournamentRequest = mockTournamentRandomGeneratorRequest();
        tournamentRequest.setCompetitors(1);
        assertThrows(TournamentException.class, () -> validator.validateTournamentRandomGeneratorRequest(tournamentRequest));
    }
}