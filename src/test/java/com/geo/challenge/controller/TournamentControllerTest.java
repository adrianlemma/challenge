package com.geo.challenge.controller;

import com.geo.challenge.exception.TournamentException;
import com.geo.challenge.service.TournamentManagementService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.geo.challenge.utils.MockUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TournamentControllerTest {

    @MockBean
    private TournamentManagementService tournamentManagementService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("test-get-tournament")
    void testGetController() throws Exception {
        when(tournamentManagementService.getTournament(any())).thenReturn(mockTournamentResponse());
        this.mockMvc.perform(get("/geopagos/tournament").content(asJsonString(mockTournamentRequest()))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(tournamentManagementService).getTournament(any());
    }

    @Test
    @DisplayName("test-post-tournament")
    void testPostController() throws Exception {
        when(tournamentManagementService.generateTournament(any())).thenReturn(mockTournamentResponse());
        this.mockMvc.perform(post("/geopagos/tournament").content(asJsonString(mockTournamentGeneratorRequest()))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(tournamentManagementService).generateTournament(any());
    }

    @Test
    @DisplayName("test-post-random-tournament")
    void testPostRandomController() throws Exception {
        when(tournamentManagementService.generateRandomTournament(any())).thenReturn(mockTournamentResponse());
        this.mockMvc.perform(post("/geopagos/tournament/random").content(asJsonString(mockTournamentRandomGeneratorRequest()))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(tournamentManagementService).generateRandomTournament(any());
    }

    @Test
    @DisplayName("test-tournament-exception-controller")
    void testTournamentExceptionController() throws Exception {
        when(tournamentManagementService.getTournament(any())).thenThrow(new TournamentException("CODE", "DESC"));
        this.mockMvc.perform(get("/geopagos/tournament").content(asJsonString(mockTournamentRequest()))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(tournamentManagementService).getTournament(any());
    }
}