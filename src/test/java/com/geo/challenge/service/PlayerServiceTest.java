package com.geo.challenge.service;

import com.geo.challenge.exception.PlayerException;
import com.geo.challenge.model.Player;
import com.geo.challenge.repository.PlayerRepository;
import com.geo.challenge.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.geo.challenge.constant.ConstantValues.*;
import static com.geo.challenge.utils.MockUtils.mockPlayer;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @InjectMocks
    private PlayerServiceImpl service;

    @Mock
    private PlayerRepository playerRepository;

    @Test
    @DisplayName("save-player-ok")
    void testSavePlayerOk() {
        when(playerRepository.save(any())).thenReturn(mockPlayer(FEMALE_LETTER, 1));
        Player result = service.save(mockPlayer(FEMALE_LETTER, 1));
        assertNotNull(result);
        verify(playerRepository).save(any());
    }

    @Test
    @DisplayName("save-player-error")
    void testSavePlayerError() {
        when(playerRepository.save(any())).thenThrow(DataIntegrityViolationException.class);
        assertThrows(PlayerException.class, () -> service.save(mockPlayer(FEMALE_LETTER, 1)));
        verify(playerRepository).save(any());
    }

    @Test
    @DisplayName("find-player-by-id")
    void testFindPlayerById() {
        when(playerRepository.findByPlayerIdAndActive(anyInt(), anyBoolean())).thenReturn(Optional.of(mockPlayer(FEMALE_LETTER, 1)));
        Player result = service.findById(1);
        assertNotNull(result);
        verify(playerRepository).findByPlayerIdAndActive(anyInt(), anyBoolean());
    }

    @Test
    @DisplayName("find-player-by-id-error")
    void testFindPlayerByIdError() {
        when(playerRepository.findByPlayerIdAndActive(anyInt(), anyBoolean())).thenReturn(Optional.empty());
        Player result = service.findById( 1);
        assertNull(result);
        verify(playerRepository).findByPlayerIdAndActive(anyInt(), anyBoolean());
    }

    @Test
    @DisplayName("get-all-players")
    void testGetAllPlayers() {
        when(playerRepository.findAll()).thenReturn(new ArrayList<>());
        List<Player> result = service.getAllPlayers();
        assertNotNull(result);
        verify(playerRepository).findAll();
    }

    @Test
    @DisplayName("player-exists-by-id")
    void testPlayerExists() {
        when(playerRepository.existsByPlayerId(anyInt())).thenReturn(true);
        Boolean result = service.exists(1);
        assertTrue(result);
        verify(playerRepository).existsByPlayerId(anyInt());
    }

    @Test
    @DisplayName("get-random-players")
    void testGetRandomPlayers() {
        when(playerRepository.findRandomPlayers(anyInt(), anyString())).thenReturn(new ArrayList<>());
        List<Player> resultM = service.findRandomPlayers(1, MALE);
        List<Player> resultF = service.findRandomPlayers(1, FEMALE);
        assertNotNull(resultM);
        assertNotNull(resultF);
        verify(playerRepository, times(2)).findRandomPlayers(anyInt(), anyString());
    }

    @Test
    @DisplayName("get-players-by-id-list")
    void testGetPlayersByIdList() {
        when(playerRepository.findPlayersByIds(any(), anyString())).thenReturn(new ArrayList<>());
        List<Player> resultM = service.findPlayersByIds(List.of(1), MALE);
        List<Player> resultF = service.findPlayersByIds(List.of(1), FEMALE);
        assertNotNull(resultM);
        assertNotNull(resultF);
        verify(playerRepository, times(2)).findPlayersByIds(any(), anyString());
    }

    @Test
    @DisplayName("count-players-by-gender")
    void testCountPlayersByGender() {
        when(playerRepository.countByGenderAndActive(anyString(), anyBoolean())).thenReturn(5);
        Integer resultM = service.countByGender(MALE);
        Integer resultF = service.countByGender(FEMALE);
        assertTrue(resultF > 0);
        assertTrue(resultM > 0);
        verify(playerRepository, times(2)).countByGenderAndActive(anyString(), anyBoolean());
    }
}