package com.geo.challenge.repository;

import com.geo.challenge.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Optional<Player> findByPlayerIdAndActive(Integer playerId, Boolean active);

    List<Player> findByActive(Boolean active);

    Boolean existsByPlayerIdAndActive(Integer playerId, Boolean active);
}
