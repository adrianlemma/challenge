package com.geo.challenge.service.impl;

import com.geo.challenge.dto.request.PlayerRequest;
import com.geo.challenge.dto.request.TournamentRequest;
import com.geo.challenge.exception.PlayerException;
import com.geo.challenge.service.RequestValidator;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.geo.challenge.constant.ConstantValues.FEMALE;
import static com.geo.challenge.constant.ConstantValues.MALE;
import static com.geo.challenge.constant.ErrorCodes.*;

@Service
public class RequestValidatorImpl implements RequestValidator {

    @Override
    public void validatePlayerRequest(PlayerRequest request) {
        if (Objects.isNull(request.getName()) || request.getName().isBlank())
            throw new PlayerException(PLAYER_NAME_ERROR.getCode(), PLAYER_NAME_ERROR.getDescription());
        if (Objects.isNull(request.getSkill()) || request.getSkill() < 0 || request.getSkill() > 100)
            throw new PlayerException(PLAYER_SKILL_ERROR.getCode(), PLAYER_SKILL_ERROR.getDescription());
        if (Objects.isNull(request.getGender()) || (!request.getGender().equalsIgnoreCase(MALE) && !request.getGender().equalsIgnoreCase(FEMALE)))
            throw new PlayerException(PLAYER_GENDER_ERROR.getCode(), PLAYER_GENDER_ERROR.getDescription());
        if (request.getGender().equalsIgnoreCase(MALE)) {
            if (Objects.isNull(request.getStrength()) || request.getStrength() < 0 || request.getStrength() > 100)
                throw new PlayerException(PLAYER_STRENGTH_ERROR.getCode(), PLAYER_STRENGTH_ERROR.getDescription());
            if (Objects.isNull(request.getSpeed()) || request.getSpeed() < 0 || request.getSpeed() > 50)
                throw new PlayerException(PLAYER_SPEED_ERROR.getCode(), PLAYER_SPEED_ERROR.getDescription());
        } else {
            if (Objects.isNull(request.getReactionTime()) || request.getReactionTime() < 0 || request.getReactionTime() > 1)
                throw new PlayerException(PLAYER_REACTION_TIME_ERROR.getCode(), PLAYER_REACTION_TIME_ERROR.getDescription());
        }
    }

    @Override
    public Boolean isTournamentRequestValid(TournamentRequest request) {
        return true;
    }

}
