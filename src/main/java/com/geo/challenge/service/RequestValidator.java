package com.geo.challenge.service;

import com.geo.challenge.dto.request.PlayerRequest;
import com.geo.challenge.dto.request.TournamentRequest;

public interface RequestValidator {

    void validatePlayerRequest(PlayerRequest request);

    Boolean isTournamentRequestValid(TournamentRequest request);

}
