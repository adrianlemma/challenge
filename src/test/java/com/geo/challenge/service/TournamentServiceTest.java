package com.geo.challenge.service;

import com.geo.challenge.dto.response.TournamentData;
import com.geo.challenge.model.Tournament;
import com.geo.challenge.repository.TournamentPhaseGameRepository;
import com.geo.challenge.repository.TournamentPhaseRepository;
import com.geo.challenge.repository.TournamentRepository;
import com.geo.challenge.service.impl.TournamentServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.geo.challenge.constant.ConstantValues.MALE;
import static com.geo.challenge.utils.MockUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TournamentServiceTest {

    @InjectMocks
    private TournamentServiceImpl service;

    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private TournamentPhaseRepository phaseRepository;

    @Mock
    private TournamentPhaseGameRepository gameRepository;

    @Test
    @DisplayName("tournament-exists-by-name")
    void testExistsByName() {
        when(tournamentRepository.existsByName(anyString())).thenReturn(true);
        Boolean result = service.existsByName("tournament_name");
        assertTrue(result);
        verify(tournamentRepository).existsByName(anyString());
    }

    @Test
    @DisplayName("tournament-count-by-date")
    void testCountByDate() {
        when(tournamentRepository.countByDate(any())).thenReturn(5);
        Integer result = service.countByDate("31/12/2023");
        assertTrue(result > 0);
        verify(tournamentRepository).countByDate(any());
    }

    @Test
    @DisplayName("tournament-count-by-date-and-type")
    void testCountByDateAdType() {
        when(tournamentRepository.countByDateAndType(any(), anyString())).thenReturn(5);
        Integer result = service.countByDateAndType("31/12/2023", MALE);
        assertTrue(result > 0);
        verify(tournamentRepository).countByDateAndType(any(), anyString());
    }

    @Test
    @DisplayName("tournament-find-by-date")
    void testFindByDate() {
        when(tournamentRepository.findByDate(any())).thenReturn(mockTournament());
        Tournament result = service.findByDate("31/12/2023");
        assertNotNull(result);
        verify(tournamentRepository).findByDate(any());
    }

    @Test
    @DisplayName("tournament-find-by-date-and-type")
    void testFindByDateAdType() {
        when(tournamentRepository.findByDateAndType(any(), anyString())).thenReturn(mockTournament());
        Tournament result = service.findByDateAndType("31/12/2023", MALE);
        assertNotNull(result);
        verify(tournamentRepository).findByDateAndType(any(), anyString());
    }

    @Test
    @DisplayName("tournament-find-by-name")
    void testFindByName() {
        when(tournamentRepository.findByName(anyString())).thenReturn(mockTournament());
        Tournament result = service.findByName("tournament_name");
        assertNotNull(result);
        verify(tournamentRepository).findByName(anyString());
    }

    @Test
    @DisplayName("tournament-save")
    void testSave() {
        when(tournamentRepository.save(any())).thenReturn(mockTournament());
        when(phaseRepository.saveAll(any())).thenReturn(mockPhaseList());
        when(gameRepository.saveAll(any())).thenReturn(new ArrayList<>());
        Tournament result = service.save(mockTournament());
        assertNotNull(result);
        verify(tournamentRepository).save(any());
        verify(phaseRepository).saveAll(any());
        verify(gameRepository, times(2)).saveAll(any());
    }

    @Test
    @DisplayName("get-tournament-list")
    void testGetTournamentList() {
        when(tournamentRepository.getTournamentList()).thenReturn(List.of(mockTournamentData()));
        List<TournamentData> result = service.getTournamentList();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}