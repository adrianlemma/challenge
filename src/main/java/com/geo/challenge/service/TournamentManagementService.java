package com.geo.challenge.service;

import com.geo.challenge.dto.request.TournamentGeneratorRequest;
import com.geo.challenge.dto.request.TournamentRandomGeneratorRequest;
import com.geo.challenge.dto.request.TournamentRequest;
import com.geo.challenge.dto.response.TournamentData;
import com.geo.challenge.dto.response.TournamentResponse;

import java.util.List;

public interface TournamentManagementService {

    TournamentResponse generateRandomTournament(TournamentRandomGeneratorRequest request);

    TournamentResponse generateTournament(TournamentGeneratorRequest request);

    TournamentResponse getTournament(TournamentRequest request);

    List<TournamentData> getTournamentList();
}
