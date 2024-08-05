package com.geo.challenge.model;

import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer playerId;
    @Column(unique = true)
    private String name;
    private Integer skill;
    private Double reactionTime;
    private Integer strength;
    private Double speed;
    private String gender;
    @Column(columnDefinition="boolean default true")
    private Boolean active;

    public Player() { }

    public Player(Integer playerId, String name, Integer skill, Double reactionTime, Integer strength, Double speed, String gender) {
        this.playerId = playerId;
        this.name = name;
        this.skill = skill;
        this.reactionTime = reactionTime;
        this.strength = strength;
        this.speed = speed;
        this.gender = gender;
        this.active = true;
    }

    public Integer getPlayerId() {
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

    public Double getReactionTime() {
        return reactionTime;
    }

    public void setReactionTime(Double reactionTime) {
        this.reactionTime = reactionTime;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
