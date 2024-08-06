package com.geo.challenge.controller;

import com.geo.challenge.exception.GenericException;
import com.geo.challenge.exception.PlayerException;
import com.geo.challenge.service.PlayerManagementService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.geo.challenge.constant.ConstantValues.FEMALE;
import static com.geo.challenge.constant.ConstantValues.MALE;
import static com.geo.challenge.utils.MockUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerControllerTest {

    @MockBean
    private PlayerManagementService playerManagementService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("test-get-player")
    void testGetController() throws Exception {
        when(playerManagementService.getPlayer(anyInt())).thenReturn(mockPlayerResponse(FEMALE));
        this.mockMvc.perform(get("/geopagos/player/1")).andExpect(status().isOk());
        verify(playerManagementService).getPlayer(anyInt());
    }

    @Test
    @DisplayName("test-get-all-player")
    void testGetAllController() throws Exception {
        when(playerManagementService.getAllPlayers()).thenReturn(mockPlayerResponse(FEMALE));
        this.mockMvc.perform(get("/geopagos/player")).andExpect(status().isOk());
        verify(playerManagementService).getAllPlayers();
    }

    @Test
    @DisplayName("test-post-player")
    void testPostController() throws Exception {
        when(playerManagementService.createPlayer(any())).thenReturn(mockPlayerResponse(MALE));
        this.mockMvc.perform(post("/geopagos/player").content(asJsonString(mockPlayerRequest(MALE)))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(playerManagementService).createPlayer(any());
    }

    @Test
    @DisplayName("test-put-player")
    void testPutController() throws Exception {
        when(playerManagementService.updatePlayer(any(), anyInt())).thenReturn(mockPlayerResponse(MALE));
        this.mockMvc.perform(put("/geopagos/player/1").content(asJsonString(mockPlayerRequest(MALE)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(playerManagementService).updatePlayer(any(), anyInt());
    }

    @Test
    @DisplayName("test-delete-player")
    void testDeleteController() throws Exception {
        this.mockMvc.perform(delete("/geopagos/player/1")).andExpect(status().isOk());
        verify(playerManagementService).deletePlayer(anyInt());
    }

    @Test
    @DisplayName("test-player-exception-controller")
    void testPlayerExceptionController() throws Exception {
        when(playerManagementService.getPlayer(anyInt())).thenThrow(new PlayerException("CODE", "DESC"));
        this.mockMvc.perform(get("/geopagos/player/1")).andExpect(status().isBadRequest());
        verify(playerManagementService).getPlayer(anyInt());
    }

    @Test
    @DisplayName("test-generic-exception-controller")
    void testGenericExceptionController() throws Exception {
        when(playerManagementService.getPlayer(anyInt())).thenThrow(new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, "CODE", "DESC"));
        this.mockMvc.perform(get("/geopagos/player/1")).andExpect(status().isInternalServerError());
        verify(playerManagementService).getPlayer(anyInt());
    }
}