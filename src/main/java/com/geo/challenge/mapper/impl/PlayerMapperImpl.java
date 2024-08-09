package com.geo.challenge.mapper.impl;

import com.geo.challenge.dto.AbstractPlayer;
import com.geo.challenge.dto.FemalePlayer;
import com.geo.challenge.dto.MalePlayer;
import com.geo.challenge.dto.request.PlayerRequest;
import com.geo.challenge.mapper.PlayerMapper;
import com.geo.challenge.model.Player;
import org.springframework.stereotype.Component;

import static com.geo.challenge.constant.ConstantValues.FEMALE;
import static com.geo.challenge.constant.ConstantValues.MALE;

@Component
public class PlayerMapperImpl implements PlayerMapper {

    @Override
    public AbstractPlayer playerToPlayerDTO(Player player) {
        if (player.getGender().equalsIgnoreCase(MALE)) {
            return new MalePlayer(
                    player.getPlayerId(),
                    player.getName(),
                    player.getSkill(),
                    player.getStrength(),
                    player.getSpeed(),
                    player.getActive()
            );
        } else {
            return new FemalePlayer(
                    player.getPlayerId(),
                    player.getName(),
                    player.getSkill(),
                    player.getReactionTime(),
                    player.getActive()
            );
        }
    }

    @Override
    public Player playerRequestToPlayer(PlayerRequest player) {
        return new Player(
                null,
                player.getName(),
                player.getSkill(),
                player.getGender().equalsIgnoreCase(MALE) ? null : player.getReactionTime(),
                player.getGender().equalsIgnoreCase(FEMALE) ? null : player.getStrength(),
                player.getGender().equalsIgnoreCase(FEMALE) ? null : player.getSpeed(),
                player.getGender().equalsIgnoreCase(MALE) ? "M" : "F"
        );
    }
}
