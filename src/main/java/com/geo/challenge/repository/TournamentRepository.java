package com.geo.challenge.repository;

import com.geo.challenge.dto.response.TournamentData;
import com.geo.challenge.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

    Boolean existsByName(String name);

    Integer countByDate(Date date);

    Integer countByDateAndType(Date date, String type);

    Tournament findByDate(Date date);

    Tournament findByDateAndType(Date date, String type);

    Tournament findByName(String name);

    @Query("SELECT new com.geo.challenge.dto.response.TournamentData(t.tournamentId, t.name, t.type) FROM Tournament AS t")
    List<TournamentData> getTournamentList();
}
