package com.geo.challenge.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerRequest {

    @JsonProperty("name")
    private String name;
    @JsonProperty("skill")
    private Integer skill;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("strength")
    private Integer strength;
    @JsonProperty("speed")
    private Double speed;
    @JsonProperty("reaction_time")
    private Double reactionTime;

    public PlayerRequest(String name, Integer skill, String gender, Integer strength, Double speed, Double reactionTime) {
        this.name = name;
        this.skill = skill;
        this.gender = gender;
        this.strength = strength;
        this.speed = speed;
        this.reactionTime = reactionTime;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Double getReactionTime() {
        return reactionTime;
    }

    public void setReactionTime(Double reactionTime) {
        this.reactionTime = reactionTime;
    }
}
