package com.geo.challenge.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TournamentData {

    @JsonProperty("tournament_id")
    private Integer tournamentId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;

    public TournamentData(Integer tournamentId, String name, String type) {
        this.tournamentId = tournamentId;
        this.name = name;
        this.type = type;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
