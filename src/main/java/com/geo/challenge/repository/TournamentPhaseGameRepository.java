package com.geo.challenge.repository;

import com.geo.challenge.model.TournamentPhaseGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentPhaseGameRepository extends JpaRepository<TournamentPhaseGame, Integer> {
}
