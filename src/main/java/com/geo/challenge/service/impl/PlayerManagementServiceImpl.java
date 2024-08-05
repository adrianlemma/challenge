package com.geo.challenge.service.impl;

import com.geo.challenge.exception.GenericException;
import com.geo.challenge.dto.request.PlayerRequest;
import com.geo.challenge.dto.response.PlayerResponse;
import com.geo.challenge.exception.PlayerException;
import com.geo.challenge.mapper.PlayerMapper;
import com.geo.challenge.model.Player;
import com.geo.challenge.service.PlayerManagementService;
import com.geo.challenge.service.PlayerService;
import com.geo.challenge.service.RequestValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.geo.challenge.constant.ErrorCodes.*;

@Service
public class PlayerManagementServiceImpl implements PlayerManagementService {

    private final PlayerService playerService;
    private final RequestValidator requestValidator;
    private final PlayerMapper playerMapper;

    public PlayerManagementServiceImpl(PlayerService playerService, RequestValidator requestValidator, PlayerMapper playerMapper) {
        this.playerService = playerService;
        this.requestValidator = requestValidator;
        this.playerMapper = playerMapper;
    }

    @Override
    public PlayerResponse getPlayer(Integer playerId) {
        if (playerId < 1)
            throw new PlayerException(INVALID_PLAYER_ID.getCode(), INVALID_PLAYER_ID.getDescription());
        Player response = playerService.findById(playerId);
        if (Objects.isNull(response))
            throw new GenericException(HttpStatus.NOT_FOUND, PLAYER_NOT_FOUND.getCode(),
                    String.format(PLAYER_NOT_FOUND.getDescription(), playerId));
        return new PlayerResponse(List.of(playerMapper.playerToPlayerDTO(response)));
    }

    @Override
    public PlayerResponse getAllPlayers() {
        List<Player> response = playerService.getAllPlayers();
        if (CollectionUtils.isEmpty(response))
            throw new GenericException(HttpStatus.NOT_FOUND, ANY_PLAYER_FOUND.getCode(), ANY_PLAYER_FOUND.getDescription());
        return new PlayerResponse(
                response.stream().map(playerMapper::playerToPlayerDTO).collect(Collectors.toList())
        );
    }

    @Override
    public PlayerResponse createPlayer(PlayerRequest request) {
        requestValidator.validatePlayerRequest(request);
        Player savedPlayer = playerService.save(playerMapper.playerRequestToPlayer(request));
        if (Objects.isNull(savedPlayer))
            throw new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_SAVING_PLAYER.getCode(), ERROR_SAVING_PLAYER.getDescription());
        return new PlayerResponse(List.of(playerMapper.playerToPlayerDTO(savedPlayer)));
    }

    @Override
    public PlayerResponse updatePlayer(PlayerRequest request, Integer playerId) {
        if (playerId < 1)
            throw new PlayerException(INVALID_PLAYER_ID.getCode(), INVALID_PLAYER_ID.getDescription());
        requestValidator.validatePlayerRequest(request);
        if (!playerService.exists(playerId))
            throw new GenericException(HttpStatus.NOT_FOUND, PLAYER_NOT_FOUND.getCode(),
                    String.format(PLAYER_NOT_FOUND.getDescription(), playerId));
        Player playerToSave = playerMapper.playerRequestToPlayer(request);
        playerToSave.setPlayerId(playerId);
        Player savedPlayer = playerService.save(playerToSave);
        if (Objects.isNull(savedPlayer))
            throw new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, PLAYER_NOT_FOUND.getCode(),
                    String.format(PLAYER_NOT_FOUND.getDescription(), playerId));
        return new PlayerResponse(List.of(playerMapper.playerToPlayerDTO(savedPlayer)));
    }

    @Override
    public void deletePlayer(Integer playerId) {
        if (playerId < 1)
            throw new PlayerException(INVALID_PLAYER_ID.getCode(), INVALID_PLAYER_ID.getDescription());
        Player player = playerService.findById(playerId);
        if (Objects.isNull(player))
            throw new GenericException(HttpStatus.NOT_FOUND, PLAYER_NOT_FOUND.getCode(),
                    String.format(PLAYER_NOT_FOUND.getDescription(), playerId));
        player.setActive(false);
        Player saved = playerService.save(player);
        if (Objects.isNull(saved))
            throw new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_DELETING_PLAYER.getCode(),
                    String.format(ERROR_DELETING_PLAYER.getDescription(), playerId));
    }
}
