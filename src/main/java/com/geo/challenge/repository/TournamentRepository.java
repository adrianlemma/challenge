package com.geo.challenge.repository;

import com.geo.challenge.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

    Boolean existsByName(String name);

    Integer countByDate(Date date);

    Integer countByDateAndType(Date date, String type);

    Tournament findByDate(Date date);

    Tournament findByDateAndType(Date date, String type);

    Tournament findByName(String name);

}
