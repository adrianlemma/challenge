package com.geo.challenge.service;

import com.geo.challenge.dto.request.TournamentRequest;
import com.geo.challenge.dto.response.TournamentResponse;
import com.geo.challenge.exception.GenericException;
import com.geo.challenge.exception.TournamentException;
import com.geo.challenge.mapper.TournamentMapper;
import com.geo.challenge.model.Player;
import com.geo.challenge.service.impl.TournamentManagementServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static com.geo.challenge.constant.ConstantValues.FEMALE;
import static com.geo.challenge.constant.ConstantValues.MALE;
import static com.geo.challenge.utils.MockUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TournamentManagementServiceTest {

    @InjectMocks
    private TournamentManagementServiceImpl service;

    @Mock
    private RequestValidator requestValidator;

    @Mock
    private TournamentService tournamentService;

    @Mock
    private TournamentMapper tournamentMapper;

    @Mock
    private PlayerService playerService;

    @Test
    @DisplayName("generate-random-tournament-ok")
    void testGenerateRandomTournamentOk() {
        List<Player> players = new ArrayList<>();
        players.add(mockPlayer(MALE, 1));
        players.add(mockPlayer(MALE, 2));
        players.add(mockPlayer(MALE, 3));
        players.add(mockPlayer(MALE, 4));
        when(tournamentService.existsByName(anyString())).thenReturn(false);
        when(playerService.countByGender(anyString())).thenReturn(4);
        when(playerService.findRandomPlayers(anyInt(), anyString())).thenReturn(players);
        when(tournamentService.save(any())).thenReturn(mockTournament());
        when(tournamentMapper.tournamentToTournamentResponse(any())).thenReturn(mockTournamentResponse());
        TournamentResponse tournament = service.generateRandomTournament(mockTournamentRandomGeneratorRequest());
        assertNotNull(tournament);
        assertFalse(tournament.getPhases().isEmpty());
        verify(requestValidator).validateTournamentRandomGeneratorRequest(any());
    }

    @Test
    @DisplayName("generate-random-tournament-duplicated")
    void testGenerateRandomTournamentDuplicated() {
        when(tournamentService.existsByName(anyString())).thenReturn(true);
        assertThrows(TournamentException.class, () ->
                service.generateRandomTournament(mockTournamentRandomGeneratorRequest()));
        verify(requestValidator).validateTournamentRandomGeneratorRequest(any());
        verifyNoInteractions(playerService);
        verifyNoInteractions(tournamentMapper);
    }

    @Test
    @DisplayName("generate-random-tournament-not-enough-players")
    void testGenerateRandomTournamentNotEnoughPlayers() {
        when(tournamentService.existsByName(anyString())).thenReturn(false);
        when(playerService.countByGender(anyString())).thenReturn(1);
        assertThrows(TournamentException.class, () ->
                service.generateRandomTournament(mockTournamentRandomGeneratorRequest()));
        verify(requestValidator).validateTournamentRandomGeneratorRequest(any());
        verifyNoInteractions(tournamentMapper);
    }

    @Test
    @DisplayName("generate-tournament-ok")
    void testGenerateTournamentOk() {
        List<Player> players = new ArrayList<>();
        players.add(mockPlayer(FEMALE, 1));
        players.add(mockPlayer(FEMALE, 2));
        players.add(mockPlayer(FEMALE, 3));
        players.add(mockPlayer(FEMALE, 4));
        when(tournamentService.existsByName(anyString())).thenReturn(false);
        when(playerService.countByGender(anyString())).thenReturn(4);
        when(playerService.findPlayersByIds(any(), anyString())).thenReturn(players);
        when(tournamentService.save(any())).thenReturn(mockTournament());
        when(tournamentMapper.tournamentToTournamentResponse(any())).thenReturn(mockTournamentResponse());
        TournamentResponse tournament = service.generateTournament(mockTournamentGeneratorRequest());
        assertNotNull(tournament);
        assertFalse(tournament.getPhases().isEmpty());
        verify(requestValidator).validateTournamentGeneratorRequest(any());
    }

    @Test
    @DisplayName("generate-tournament-duplicated")
    void testGenerateTournamentDuplicated() {
        when(tournamentService.existsByName(anyString())).thenReturn(true);
        assertThrows(TournamentException.class, () ->
                service.generateTournament(mockTournamentGeneratorRequest()));
        verify(requestValidator).validateTournamentGeneratorRequest(any());
        verifyNoInteractions(playerService);
        verifyNoInteractions(tournamentMapper);
    }

    @Test
    @DisplayName("generate-tournament-not-enough-players")
    void testGenerateTournamentNotEnoughPlayers() {
        List<Player> players = new ArrayList<>();
        when(tournamentService.existsByName(anyString())).thenReturn(false);
        when(playerService.findPlayersByIds(any(), anyString())).thenReturn(players);
        assertThrows(TournamentException.class, () ->
                service.generateTournament(mockTournamentGeneratorRequest()));
        verify(requestValidator).validateTournamentGeneratorRequest(any());
        verifyNoInteractions(tournamentMapper);
    }

    @Test
    @DisplayName("get-tournament-by-name")
    void testGetTournamentByName() {
        when(tournamentService.findByName(anyString())).thenReturn(mockTournament());
        when(tournamentMapper.tournamentToTournamentResponse(any())).thenReturn(mockTournamentResponse());
        TournamentResponse tournament = service.getTournament(mockTournamentRequest());
        assertNotNull(tournament);
        assertFalse(tournament.getPhases().isEmpty());
        verify(requestValidator).validateTournamentRequest(any());
    }

    @Test
    @DisplayName("get-tournament-by-date-and-type")
    void testGetTournamentByDateAndType() {
        TournamentRequest request = mockTournamentRequest();
        request.setName(null);
        when(tournamentService.findByDateAndType(anyString(), anyString())).thenReturn(mockTournament());
        when(tournamentMapper.tournamentToTournamentResponse(any())).thenReturn(mockTournamentResponse());
        TournamentResponse tournament = service.getTournament(request);
        assertNotNull(tournament);
        assertFalse(tournament.getPhases().isEmpty());
        verify(requestValidator).validateTournamentRequest(any());
    }

    @Test
    @DisplayName("get-tournament-duplicated-by-date")
    void testGetTournamentDuplicatedByDate() {
        TournamentRequest request = mockTournamentRequest();
        request.setName(null);
        request.setType(null);
        when(tournamentService.countByDate(anyString())).thenReturn(2);
        assertThrows(TournamentException.class, () -> service.getTournament(request));
        verify(requestValidator).validateTournamentRequest(any());
    }

    @Test
    @DisplayName("get-tournament-duplicated-by-date-and-type")
    void testGetTournamentDuplicatedByDateAndType() {
        TournamentRequest request = mockTournamentRequest();
        request.setName(null);
        when(tournamentService.countByDateAndType(anyString(), anyString())).thenReturn(2);
        assertThrows(TournamentException.class, () -> service.getTournament(request));
        verify(requestValidator).validateTournamentRequest(any());
    }

    @Test
    @DisplayName("get-tournament-by-date")
    void testGetTournamentByDate() {
        TournamentRequest request = mockTournamentRequest();
        request.setName(null);
        request.setType(null);
        when(tournamentService.findByDate(anyString())).thenReturn(mockTournament());
        when(tournamentMapper.tournamentToTournamentResponse(any())).thenReturn(mockTournamentResponse());
        TournamentResponse tournament = service.getTournament(request);
        assertNotNull(tournament);
        assertFalse(tournament.getPhases().isEmpty());
        verify(requestValidator).validateTournamentRequest(any());
    }

    @Test
    @DisplayName("get-tournament-not-found")
    void testGetTournamentNotFound() {
        when(tournamentService.findByName(anyString())).thenReturn(null);
        assertThrows(GenericException.class, () -> service.getTournament(mockTournamentRequest()));
        verify(requestValidator).validateTournamentRequest(any());
    }
}