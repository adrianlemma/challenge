package com.geo.challenge.service.impl;

import com.geo.challenge.dto.request.PlayerRequest;
import com.geo.challenge.dto.request.TournamentGeneratorRequest;
import com.geo.challenge.dto.request.TournamentRandomGeneratorRequest;
import com.geo.challenge.dto.request.TournamentRequest;
import com.geo.challenge.exception.PlayerException;
import com.geo.challenge.exception.TournamentException;
import com.geo.challenge.service.RequestValidator;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
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
    public void validateTournamentRequest(TournamentRequest request) {
        boolean isName = !Objects.isNull(request.getName());
        boolean isDate = !Objects.isNull(request.getDate());
        boolean isType = !Objects.isNull(request.getType());

        if (isName && request.getName().isBlank())
            throw new TournamentException(TOURNAMENT_NAME_ERROR.getCode(), TOURNAMENT_NAME_ERROR.getDescription());
        if (isDate && !isValidDate(request.getDate()))
            throw new TournamentException(TOURNAMENT_DATE_ERROR.getCode(), TOURNAMENT_DATE_ERROR.getDescription());
        if (isType && !request.getType().equalsIgnoreCase(MALE) && !request.getType().equalsIgnoreCase(FEMALE))
            throw new TournamentException(TOURNAMENT_TYPE_ERROR.getCode(), TOURNAMENT_TYPE_ERROR.getDescription());

        if (!isName && !isDate)
            throw new TournamentException(INSUFFICIENT_PARAMETERS.getCode(), INSUFFICIENT_PARAMETERS.getDescription());
    }

    @Override
    public void validateTournamentRandomGeneratorRequest(TournamentRandomGeneratorRequest request) {
        validateCommonFields(request.getName(), request.getType(), request.getDate());
        if (Objects.isNull(request.getCompetitors()) || !isValidCompetitorsQuantity(request.getCompetitors()))
            throw new TournamentException(TOURNAMENT_COMPETITORS_ERROR.getCode(), TOURNAMENT_COMPETITORS_ERROR.getDescription());
    }

    @Override
    public void validateTournamentGeneratorRequest(TournamentGeneratorRequest request) {
        validateCommonFields(request.getName(), request.getType(), request.getDate());
        if (CollectionUtils.isEmpty(request.getPlayerIds()))
            throw new TournamentException(EMPTY_PLAYER_LIST.getCode(), EMPTY_PLAYER_LIST.getDescription());
        if (!isValidCompetitorsQuantity(request.getPlayerIds().size()))
            throw new TournamentException(TOURNAMENT_COMPETITORS_ERROR.getCode(), TOURNAMENT_COMPETITORS_ERROR.getDescription());
    }

    private void validateCommonFields(String name, String type, String date) {
        if (Objects.isNull(name) || name.isBlank())
            throw new TournamentException(TOURNAMENT_NAME_ERROR.getCode(), TOURNAMENT_NAME_ERROR.getDescription());
        if (Objects.isNull(type) || (!type.equalsIgnoreCase(MALE) && !type.equalsIgnoreCase(FEMALE)))
            throw new TournamentException(TOURNAMENT_TYPE_ERROR.getCode(), TOURNAMENT_TYPE_ERROR.getDescription());
        if (Objects.isNull(date) || !isValidDate(date))
            throw new TournamentException(TOURNAMENT_DATE_ERROR.getCode(), TOURNAMENT_DATE_ERROR.getDescription());
    }

    private Boolean isValidDate(String date) {
        if (date.length() != 10)
            return false;
        try {
            String[] dateParts = date.split("/");
            LocalDate dateOk = LocalDate.of(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[0]));
            return dateOk.getYear() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean isValidCompetitorsQuantity(Integer competitors) {
        for (int i = competitors; i > 1; i /= 2) {
            if (i % 2 != 0) {
                return false;
            }
        }
        return competitors != 1;
    }
}
