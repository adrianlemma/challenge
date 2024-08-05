package com.geo.challenge.service;

import com.geo.challenge.dto.request.PlayerRequest;
import com.geo.challenge.dto.response.PlayerResponse;

public interface PlayerManagementService {

    PlayerResponse getPlayer(Integer playerId);

    PlayerResponse getAllPlayers();

    PlayerResponse createPlayer(PlayerRequest request);

    PlayerResponse updatePlayer(PlayerRequest request, Integer playerId);

    void deletePlayer(Integer playerId);
}
