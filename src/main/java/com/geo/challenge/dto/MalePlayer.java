package com.geo.challenge.dto;

import static com.geo.challenge.constant.ConstantValues.MALE_LETTER;

public class MalePlayer extends AbstractPlayer {

    private Integer strength;
    private Double speed;

    public MalePlayer() {
        super();
    }

    public MalePlayer(Integer playerId, String name, Integer luck, Integer strength, Double speed, Boolean active) {
        super(playerId, name, luck, active, MALE_LETTER);
        this.strength = strength;
        this.speed = speed;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }
}
