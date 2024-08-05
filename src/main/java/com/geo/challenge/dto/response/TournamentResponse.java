package com.geo.challenge.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.geo.challenge.dto.AbstractPlayer;

import java.util.ArrayList;
import java.util.List;

public class TournamentResponse {

    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("competitors")
    private Integer competitors;
    @JsonProperty("date")
    private String date;
    @JsonProperty("winner")
    AbstractPlayer winner;
    @JsonProperty("phases")
    List<TournamentPhaseResponse> phases;

    public TournamentResponse() {
        this.phases = new ArrayList<>();
    }

    public TournamentResponse(String name, String type, Integer competitors, String date, AbstractPlayer winner, List<TournamentPhaseResponse> phases) {
        this.name = name;
        this.type = type;
        this.competitors = competitors;
        this.date = date;
        this.winner = winner;
        this.phases = phases;
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

    public Integer getCompetitors() {
        return competitors;
    }

    public void setCompetitors(Integer competitors) {
        this.competitors = competitors;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public AbstractPlayer getWinner() {
        return winner;
    }

    public void setWinner(AbstractPlayer winner) {
        this.winner = winner;
    }

    public List<TournamentPhaseResponse> getPhases() {
        return phases;
    }

    public void setPhases(List<TournamentPhaseResponse> phases) {
        this.phases = phases;
    }
}
