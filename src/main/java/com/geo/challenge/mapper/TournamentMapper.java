package com.geo.challenge.mapper;

import com.geo.challenge.dto.response.TournamentResponse;
import com.geo.challenge.model.Tournament;

public interface TournamentMapper {

    TournamentResponse tournamentToTournamentResponse(Tournament tournament);
}
