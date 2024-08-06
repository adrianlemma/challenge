package com.geo.challenge.mapper;

import com.geo.challenge.dto.response.TournamentResponse;
import com.geo.challenge.mapper.impl.TournamentMapperImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.geo.challenge.utils.MockUtils.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TournamentMapperTest {

    @InjectMocks
    private TournamentMapperImpl mapper;

    @Mock
    private PlayerMapper playerMapper;

    @Test
    @DisplayName("tournament_mapper_ok")
    void tournamentMapper() {
        when(playerMapper.playerToPlayerDTO(any())).thenReturn(mockMalePlayer());
        TournamentResponse result = mapper.tournamentToTournamentResponse(mockTournament());
        assertNotNull(result);
        assertNotNull(result.getWinner());
        assertNotNull(result.getPhases());
        assertFalse(result.getPhases().isEmpty());
        verify(playerMapper).playerToPlayerDTO(any());
    }
}
