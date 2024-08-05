package com.geo.challenge.service;

import com.geo.challenge.dto.request.TournamentGeneratorRequest;
import com.geo.challenge.dto.request.TournamentRandomGeneratorRequest;
import com.geo.challenge.dto.request.TournamentRequest;
import com.geo.challenge.dto.response.TournamentResponse;

public interface TournamentManagementService {

    TournamentResponse generateRandomTournament(TournamentRandomGeneratorRequest request);

    TournamentResponse generateTournament(TournamentGeneratorRequest request);

    TournamentResponse getTournament(TournamentRequest request);

}
