package com.geo.challenge.mapper;

import com.geo.challenge.dto.AbstractPlayer;
import com.geo.challenge.dto.request.PlayerRequest;
import com.geo.challenge.model.Player;

public interface PlayerMapper {

    AbstractPlayer playerToPlayerDTO(Player player);

    Player playerRequestToPlayer(PlayerRequest player);

}
