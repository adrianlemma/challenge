package com.geo.challenge.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TournamentRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("date")
    private String date;

    public TournamentRequest(String name, String type, String date) {
        this.name = name;
        this.type = type;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
