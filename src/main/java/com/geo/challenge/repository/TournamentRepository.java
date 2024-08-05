package com.geo.challenge.repository;

import com.geo.challenge.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
}
