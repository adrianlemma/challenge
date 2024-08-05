package com.geo.challenge.service;

import com.geo.challenge.model.Tournament;
import com.geo.challenge.model.TournamentPhase;

import java.util.List;

public interface TournamentService {

    Boolean existsByName(String name);

    Integer countByDate(String date);

    Integer countByDateAndType(String date, String type);

    Tournament findByDate(String date);

    Tournament findByDateAndType(String date, String type);

    Tournament findByName(String name);

    Tournament save(Tournament tournament);

}
