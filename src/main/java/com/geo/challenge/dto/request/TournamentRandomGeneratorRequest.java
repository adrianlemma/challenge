package com.geo.challenge.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TournamentRandomGeneratorRequest extends TournamentRequest {

    @JsonProperty("competitors")
    private Integer competitors;

    public TournamentRandomGeneratorRequest(String name, String type, String date, Integer competitors) {
        super(name, type, date);
        this.competitors = competitors;
    }

    public Integer getCompetitors() {
        return competitors;
    }

    public void setCompetitors(Integer competitors) {
        this.competitors = competitors;
    }
}
