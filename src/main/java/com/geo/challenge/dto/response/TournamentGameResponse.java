package com.geo.challenge.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TournamentGameResponse {

    @JsonProperty("game_id")
    private Integer gameId;

    @JsonProperty("winner_name")
    private String winner;

    @JsonProperty("loser_name")
    private String loser;

    public TournamentGameResponse() { }

    public TournamentGameResponse(Integer gameId, String winner, String loser) {
        this.gameId = gameId;
        this.winner = winner;
        this.loser = loser;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getLoser() {
        return loser;
    }

    public void setLoser(String loser) {
        this.loser = loser;
    }
}
