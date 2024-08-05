package com.geo.challenge.dto;

public class FemalePlayer extends AbstractPlayer {

    private Double reactionTime;

    public FemalePlayer() {
        super();
    }

    public FemalePlayer(Integer playerId, String name, Integer luck, Double reactionTime) {
        super(playerId, name, luck);
        this.reactionTime = reactionTime;
    }

    public void setReactionTime(Double reactionTime) {
        this.reactionTime = reactionTime;
    }

    public Double getReactionTime() {
        return reactionTime;
    }
}
