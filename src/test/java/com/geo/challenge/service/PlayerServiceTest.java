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

import static com.geo.challenge.constant.ConstantValues.FEMALE;
import static com.geo.challenge.constant.ConstantValues.MALE;
import static com.geo.challenge.utils.MockUtils.mockPlayer;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @InjectMocks
    private PlayerServiceImpl service;

    @Mock
    private PlayerRepository playerRepository;

    @Test
    @DisplayName("save-player-ok")
    void testSavePlayerOk() {
        when(playerRepository.save(any())).thenReturn(mockPlayer(FEMALE, 1));
        Player result = service.save(mockPlayer(FEMALE, 1));
        assertNotNull(result);
        verify(playerRepository).save(any());
    }

    @Test
    @DisplayName("save-player-error")
    void testSavePlayerError() {
        when(playerRepository.save(any())).thenThrow(DataIntegrityViolationException.class);
        assertThrows(PlayerException.class, () -> service.save(mockPlayer(FEMALE, 1)));
        verify(playerRepository).save(any());
    }

    @Test
    @DisplayName("find-player-by-id")
    void testFindPlayerById() {
        when(playerRepository.findByPlayerIdAndActive(anyInt(), anyBoolean())).thenReturn(Optional.of(mockPlayer(FEMALE, 1)));
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
        when(playerRepository.findByActive(anyBoolean())).thenReturn(new ArrayList<>());
        List<Player> result = service.getAllPlayers();
        assertNotNull(result);
        verify(playerRepository).findByActive(anyBoolean());
    }

    @Test
    @DisplayName("player-exists-by-id")
    void testPlayerExists() {
        when(playerRepository.existsByPlayerIdAndActive(anyInt(), anyBoolean())).thenReturn(true);
        Boolean result = service.exists(1);
        assertTrue(result);
        verify(playerRepository).existsByPlayerIdAndActive(anyInt(), anyBoolean());
    }

    @Test
    @DisplayName("get-random-players")
    void testGetRandomPlayers() {
        when(playerRepository.findRandomPlayers(anyInt(), anyString())).thenReturn(new ArrayList<>());
        List<Player> result = service.findRandomPlayers(1, MALE);
        assertNotNull(result);
        verify(playerRepository).findRandomPlayers(anyInt(), anyString());
    }

    @Test
    @DisplayName("get-players-by-id-list")
    void testGetPlayersByIdList() {
        when(playerRepository.findPlayersByIds(any(), anyString())).thenReturn(new ArrayList<>());
        List<Player> result = service.findPlayersByIds(List.of(1), MALE);
        assertNotNull(result);
        verify(playerRepository).findPlayersByIds(any(), anyString());
    }

    @Test
    @DisplayName("count-players-by-gender")
    void testCountPlayersByGender() {
        when(playerRepository.countByGender(anyString())).thenReturn(5);
        Integer result = service.countByGender(MALE);
        assertTrue(result > 0);
        verify(playerRepository).countByGender(anyString());
    }
}