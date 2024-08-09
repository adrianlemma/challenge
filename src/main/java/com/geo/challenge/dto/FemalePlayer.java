package com.geo.challenge.dto;

import static com.geo.challenge.constant.ConstantValues.FEMALE_LETTER;

public class FemalePlayer extends AbstractPlayer {

    private Double reactionTime;

    public FemalePlayer() {
        super();
    }

    public FemalePlayer(Integer playerId, String name, Integer luck, Double reactionTime, Boolean active) {
        super(playerId, name, luck, active, FEMALE_LETTER);
        this.reactionTime = reactionTime;
    }

    public void setReactionTime(Double reactionTime) {
        this.reactionTime = reactionTime;
    }

    public Double getReactionTime() {
        return reactionTime;
    }
}
