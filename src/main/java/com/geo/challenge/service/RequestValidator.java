package com.geo.challenge.service;

import com.geo.challenge.dto.request.PlayerRequest;
import com.geo.challenge.dto.request.TournamentGeneratorRequest;
import com.geo.challenge.dto.request.TournamentRandomGeneratorRequest;
import com.geo.challenge.dto.request.TournamentRequest;

public interface RequestValidator {

    void validatePlayerRequest(PlayerRequest request);

    void validateTournamentRequest(TournamentRequest request);

    void validateTournamentRandomGeneratorRequest(TournamentRandomGeneratorRequest request);

    void validateTournamentGeneratorRequest(TournamentGeneratorRequest request);

}
