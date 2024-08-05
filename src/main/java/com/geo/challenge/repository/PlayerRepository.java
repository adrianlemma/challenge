package com.geo.challenge.repository;

import com.geo.challenge.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Optional<Player> findByPlayerIdAndActive(Integer playerId, Boolean active);

    List<Player> findByActive(Boolean active);

    Boolean existsByPlayerIdAndActive(Integer playerId, Boolean active);

    Integer countByGender(String gender);

    @Query(value = "SELECT * FROM Player WHERE gender = :gender ORDER BY RANDOM() LIMIT :quantity", nativeQuery = true)
    List<Player> findRandomPlayers(@Param("quantity") Integer quantity, @Param("gender") String gender);

    @Query(value = "SELECT * FROM Player WHERE gender = :gender AND player_id IN :playerIds ORDER BY RANDOM()", nativeQuery = true)
    List<Player> findPlayersByIds(@Param("playerIds") List<Integer> playerIds, @Param("gender") String gender);
}
