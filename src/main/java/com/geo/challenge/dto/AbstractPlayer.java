package com.geo.challenge.dto;

public abstract class AbstractPlayer {

    private Integer playerId;
    private String name;
    private Integer skill;

    protected AbstractPlayer() { }

    protected AbstractPlayer(Integer playerId, String name, Integer skill) {
        this.playerId = playerId;
        this.name = name;
        this.skill = skill;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSkill() {
        return skill;
    }

    public void setSkill(Integer skill) {
        this.skill = skill;
    }
}
