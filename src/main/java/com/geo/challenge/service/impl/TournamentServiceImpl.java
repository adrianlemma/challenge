package com.geo.challenge.service.impl;

import com.geo.challenge.model.Tournament;
import com.geo.challenge.repository.TournamentPhaseGameRepository;
import com.geo.challenge.repository.TournamentPhaseRepository;
import com.geo.challenge.repository.TournamentRepository;
import com.geo.challenge.service.TournamentService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentPhaseRepository tournamentPhaseRepository;
    private final TournamentPhaseGameRepository tournamentPhaseGameRepository;

    public TournamentServiceImpl(TournamentRepository tournamentRepository, TournamentPhaseRepository tournamentPhaseRepository, TournamentPhaseGameRepository tournamentPhaseGameRepository) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentPhaseRepository = tournamentPhaseRepository;
        this.tournamentPhaseGameRepository = tournamentPhaseGameRepository;
    }

    @Override
    public Boolean existsByName(String name) {
        return tournamentRepository.existsByName(name);
    }

    @Override
    public Integer countByDate(String date) {
        return tournamentRepository.countByDate(getDate(date));
    }

    @Override
    public Integer countByDateAndType(String date, String type) {
        return tournamentRepository.countByDateAndType(getDate(date), type);
    }

    @Override
    public Tournament findByDate(String date) {
        return tournamentRepository.findByDate(getDate(date));
    }

    @Override
    public Tournament findByDateAndType(String date, String type) {
        return tournamentRepository.findByDateAndType(getDate(date), type);
    }

    @Override
    public Tournament findByName(String name) {
        return tournamentRepository.findByName(name);
    }

    @Override
    public Tournament save(Tournament tournament) {
        Tournament saved = tournamentRepository.save(tournament);
        saved.setPhases(tournamentPhaseRepository.saveAll(tournament.getPhases()));
        saved.getPhases().forEach(phase -> phase.setGames(tournamentPhaseGameRepository.saveAll(phase.getGames())));
        return saved;
    }

    private Date getDate(String stringDate) {
        String[] dateParts = stringDate.split("/");
        LocalDate date = LocalDate.of(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[0]));
        return Date.valueOf(date);
    }
}
