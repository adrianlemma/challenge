package com.geo.challenge.service;

import com.geo.challenge.dto.response.PlayerResponse;
import com.geo.challenge.exception.GenericException;
import com.geo.challenge.exception.PlayerException;
import com.geo.challenge.mapper.PlayerMapper;
import com.geo.challenge.service.impl.PlayerManagementServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static com.geo.challenge.constant.ConstantValues.FEMALE;
import static com.geo.challenge.constant.ConstantValues.MALE;
import static com.geo.challenge.utils.MockUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PlayerManagementServiceTest {

    @InjectMocks
    private PlayerManagementServiceImpl service;

    @Mock
    private RequestValidator requestValidator;

    @Mock
    private PlayerService playerService;

    @Mock
    private PlayerMapper playerMapper;

    @Test
    @DisplayName("get-player-ok")
    void testGetPlayerOk() {
        when(playerService.findById(anyInt())).thenReturn(mockPlayer(MALE, 1));
        when(playerMapper.playerToPlayerDTO(any())).thenReturn(mockMalePlayer());
        PlayerResponse player = service.getPlayer(1);
        assertNotNull(player);
        assertFalse(player.getData().isEmpty());
    }

    @Test
    @DisplayName("get-player-invalid-id")
    void testGetPlayerInvalidId() {
        assertThrows(PlayerException.class, () -> service.getPlayer(-1));
        verifyNoInteractions(playerService);
        verifyNoInteractions(playerMapper);
    }

    @Test
    @DisplayName("get-player-not-found")
    void testGetPlayerNotFound() {
        when(playerService.findById(anyInt())).thenReturn(null);
        assertThrows(GenericException.class, () -> service.getPlayer(1));
        verify(playerService).findById(anyInt());
        verifyNoInteractions(playerMapper);
    }

    @Test
    @DisplayName("get-all-players-ok")
    void testGetAllPlayersOk() {
        when(playerService.getAllPlayers()).thenReturn(List.of(mockPlayer(MALE, 1)));
        when(playerMapper.playerToPlayerDTO(any())).thenReturn(mockMalePlayer());
        PlayerResponse player = service.getAllPlayers();
        assertNotNull(player);
        assertFalse(player.getData().isEmpty());
    }

    @Test
    @DisplayName("get-all-players-not-found")
    void testGetAllPlayersNotFound() {
        when(playerService.getAllPlayers()).thenReturn(null);
        assertThrows(GenericException.class, () -> service.getAllPlayers());
        verify(playerService).getAllPlayers();
        verifyNoInteractions(playerMapper);
    }

    @Test
    @DisplayName("create-player-ok")
    void testCreatePlayerOk() {
        when(playerService.save(any())).thenReturn(mockPlayer(FEMALE, 1));
        when(playerMapper.playerToPlayerDTO(any())).thenReturn(mockMalePlayer());
        PlayerResponse player = service.createPlayer(mockPlayerRequest(FEMALE));
        assertNotNull(player);
        assertFalse(player.getData().isEmpty());
        verify(requestValidator).validatePlayerRequest(any());
    }

    @Test
    @DisplayName("create-player-unexpected-error")
    void testCreatePlayerUnexpectedError() {
        when(playerService.save(any())).thenReturn(null);
        assertThrows(GenericException.class, () -> service.createPlayer(mockPlayerRequest(FEMALE)));
        verify(playerService).save(any());
        verify(playerMapper).playerRequestToPlayer(any());
        verify(playerMapper, times(0)).playerToPlayerDTO(any());
    }

    @Test
    @DisplayName("update-player-ok")
    void testUpdatePlayerOk() {
        when(playerService.exists(anyInt())).thenReturn(true);
        when(playerService.save(any())).thenReturn(mockPlayer(MALE, 1));
        when(playerMapper.playerRequestToPlayer(any())).thenReturn(mockPlayer(MALE, 1));
        when(playerMapper.playerToPlayerDTO(any())).thenReturn(mockMalePlayer());
        PlayerResponse player = service.updatePlayer(mockPlayerRequest(MALE), 1);
        assertNotNull(player);
        assertFalse(player.getData().isEmpty());
        verify(requestValidator).validatePlayerRequest(any());
    }

    @Test
    @DisplayName("update-player-invalid-id")
    void testUpdatePlayerInvalidId() {
        assertThrows(PlayerException.class, () -> service.updatePlayer(mockPlayerRequest(MALE), -1));
        verifyNoInteractions(playerService);
        verifyNoInteractions(playerMapper);
        verifyNoInteractions(requestValidator);
    }

    @Test
    @DisplayName("update-player-not-found")
    void testUpdatePlayerNotFound() {
        when(playerService.exists(anyInt())).thenReturn(false);
        assertThrows(GenericException.class, () -> service.updatePlayer(mockPlayerRequest(MALE), 1));
        verify(playerService).exists(anyInt());
        verify(playerService, times(0)).save(any());
        verify(requestValidator).validatePlayerRequest(any());
        verifyNoInteractions(playerMapper);
    }

    @Test
    @DisplayName("update-player-unexpected-error")
    void testUpdatePlayerUnexpectedError() {
        when(playerService.exists(anyInt())).thenReturn(true);
        when(playerService.save(any())).thenReturn(null);
        when(playerMapper.playerRequestToPlayer(any())).thenReturn(mockPlayer(MALE, 1));
        assertThrows(GenericException.class,() -> service.updatePlayer(mockPlayerRequest(MALE), 1));
        verify(requestValidator).validatePlayerRequest(any());
        verify(playerService).exists(anyInt());
        verify(playerService).save(any());
        verify(playerMapper).playerRequestToPlayer(any());
        verify(playerMapper, times(0)).playerToPlayerDTO(any());
    }

    @Test
    @DisplayName("delete-player-ok")
    void testDeletePlayerOk() {
        when(playerService.findById(anyInt())).thenReturn(mockPlayer(MALE, 1));
        when(playerService.save(any())).thenReturn(mockPlayer(MALE, 1));
        service.deletePlayer(1);
        verify(playerService).findById(anyInt());
        verify(playerService).save(any());
    }

    @Test
    @DisplayName("delete-player-invalid-id")
    void testDeletePlayerInvalidId() {
        assertThrows(PlayerException.class, () -> service.deletePlayer(-1));
        verifyNoInteractions(playerService);
    }

    @Test
    @DisplayName("delete-player-not-found")
    void testDeletePlayerNotFound() {
        when(playerService.findById(anyInt())).thenReturn(null);
        assertThrows(GenericException.class, () -> service.deletePlayer(1));
        verify(playerService).findById(anyInt());
        verify(playerService, times(0)).save(any());
    }

    @Test
    @DisplayName("delete-player-unexpected-error")
    void testDeletePlayerUnexpectedError() {
        when(playerService.findById(anyInt())).thenReturn(mockPlayer(MALE, 1));
        when(playerService.save(any())).thenReturn(null);
        assertThrows(GenericException.class, () -> service.deletePlayer(1));
        verify(playerService).findById(anyInt());
        verify(playerService).save(any());
    }
}