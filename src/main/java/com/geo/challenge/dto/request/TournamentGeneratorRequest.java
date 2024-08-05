package com.geo.challenge.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TournamentGeneratorRequest extends TournamentRequest {

    @JsonProperty("player_ids")
    private List<Integer> playerIds;

    public TournamentGeneratorRequest(String name, String type, String date, List<Integer> playerIds) {
        super(name, type, date);
        this.playerIds = playerIds;
    }

    public List<Integer> getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(List<Integer> playerIds) {
        this.playerIds = playerIds;
    }
}
