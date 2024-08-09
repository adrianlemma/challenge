package com.geo.challenge.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TournamentListResponse {

    @JsonProperty("tournaments")
    private List<TournamentData> tournaments;

    public TournamentListResponse(List<TournamentData> tournaments) {
        this.tournaments = tournaments;
    }

    public List<TournamentData> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<TournamentData> tournaments) {
        this.tournaments = tournaments;
    }
}
