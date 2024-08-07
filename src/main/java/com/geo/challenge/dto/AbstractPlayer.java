package com.geo.challenge.dto;

public abstract class AbstractPlayer {

    private Integer playerId;
    private String name;
    private Integer skill;
    private Boolean active;

    protected AbstractPlayer() { }

    protected AbstractPlayer(Integer playerId, String name, Integer skill, Boolean active) {
        this.playerId = playerId;
        this.name = name;
        this.skill = skill;
        this.active = active;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
