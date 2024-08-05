package com.geo.challenge.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "tournament")
public class Tournament {

    @Id
    @Column(name = "tournament_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tournamentId;

    private String description;
    private Date date;
    private String type;

    @OneToOne
    @JoinColumn(referencedColumnName = "player_id")
    private Player winner;

    public Tournament() { }

    public Tournament(Integer tournamentId, String description, Date date, String type, Player winner) {
        this.tournamentId = tournamentId;
        this.description = description;
        this.date = date;
        this.type = type;
        this.winner = winner;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
