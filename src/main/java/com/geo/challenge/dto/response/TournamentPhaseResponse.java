package com.geo.challenge.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TournamentPhaseResponse {

    @JsonProperty("phase_number")
    private Integer phaseNumber;

    @JsonProperty("phase_games")
    private List<TournamentGameResponse> phaseGames;

    public TournamentPhaseResponse() { }

    public Integer getPhaseNumber() {
        return phaseNumber;
    }

    public void setPhaseNumber(Integer phaseNumber) {
        this.phaseNumber = phaseNumber;
    }

    public List<TournamentGameResponse> getPhaseGames() {
        return phaseGames;
    }

    public void setPhaseGames(List<TournamentGameResponse> phaseGames) {
        this.phaseGames = phaseGames;
    }
}
