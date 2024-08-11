package com.geo.challenge.service.impl;

import com.geo.challenge.exception.PlayerException;
import com.geo.challenge.model.Player;
import com.geo.challenge.repository.PlayerRepository;
import com.geo.challenge.service.PlayerService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.geo.challenge.constant.ConstantValues.*;
import static com.geo.challenge.constant.ErrorCodes.PLAYER_NAME_DUPLICATED;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player save(Player player) {
        try {
            return playerRepository.save(player);
        } catch (DataIntegrityViolationException ex) {
            throw new PlayerException(PLAYER_NAME_DUPLICATED.getCode(), String.format(PLAYER_NAME_DUPLICATED.getDescription(), player.getName()));
        }
    }

    @Override
    public Player findById(Integer playerId) {
        return playerRepository.findByPlayerIdAndActive(playerId, true).orElse(null);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Boolean exists(Integer playerId) {
        return playerRepository.existsByPlayerId(playerId);
    }

    @Override
    public List<Player> findRandomPlayers(Integer quantity, String gender) {
        String genderDB = gender.equalsIgnoreCase(MALE) ? MALE_LETTER : FEMALE_LETTER;
        return playerRepository.findRandomPlayers(quantity, genderDB);
    }

    @Override
    public List<Player> findPlayersByIds(List<Integer> playerIds, String gender) {
        String genderDB = gender.equalsIgnoreCase(MALE) ? MALE_LETTER : FEMALE_LETTER;
        return playerRepository.findPlayersByIds(playerIds, genderDB);
    }

    @Override
    public Integer countByGender(String gender) {
        String genderDB = gender.equalsIgnoreCase(MALE) ? MALE_LETTER : FEMALE_LETTER;
        return playerRepository.countByGenderAndActive(genderDB, true);
    }
}
