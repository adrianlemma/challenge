package com.geo.challenge.service;

import com.geo.challenge.model.Player;

import java.util.List;

public interface PlayerService {

    Player save(Player player);

    Player findById(Integer playerId);

    List<Player> getAllPlayers();

    Boolean exists(Integer playerId);

    List<Player> findRandomPlayers(Integer quantity, String gender);

    List<Player> findPlayersByIds(List<Integer> playerIds, String gender);

    Integer countByGender(String gender);
}
