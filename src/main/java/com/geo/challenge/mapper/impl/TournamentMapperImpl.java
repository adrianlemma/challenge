package com.geo.challenge.mapper.impl;

import com.geo.challenge.dto.response.TournamentGameResponse;
import com.geo.challenge.dto.response.TournamentPhaseResponse;
import com.geo.challenge.dto.response.TournamentResponse;
import com.geo.challenge.mapper.PlayerMapper;
import com.geo.challenge.mapper.TournamentMapper;
import com.geo.challenge.model.Tournament;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TournamentMapperImpl implements TournamentMapper {

    private final PlayerMapper playerMapper;

    public TournamentMapperImpl(PlayerMapper playerMapper) {
        this.playerMapper = playerMapper;
    }

    @Override
    public TournamentResponse tournamentToTournamentResponse(Tournament tournament) {
        TournamentResponse tournamentResponse = new TournamentResponse();
        tournamentResponse.setName(tournament.getName());
        tournamentResponse.setDate(tournament.getDate().toString());
        tournamentResponse.setType(tournament.getType());
        tournamentResponse.setCompetitors(tournament.getCompetitors());
        tournamentResponse.setWinner(playerMapper.playerToPlayerDTO(tournament.getWinner()));

        tournament.getPhases().forEach(phase -> {
            TournamentPhaseResponse newPhase = new TournamentPhaseResponse();
            newPhase.setPhaseNumber(phase.getPhaseNumber());
            newPhase.setPhaseGames(phase.getGames().stream().map(game ->
                    new TournamentGameResponse(game.getGameId(), game.getWinner().getName(), game.getLoser().getName())).collect(Collectors.toList()));
            tournamentResponse.getPhases().add(newPhase);
        });

        return tournamentResponse;
    }

}
