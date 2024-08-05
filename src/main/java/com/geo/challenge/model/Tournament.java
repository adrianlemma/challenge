package com.geo.challenge.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tournament")
public class Tournament {

    @Id
    @Column(name = "tournament_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tournamentId;
    @Column(unique = true)
    private String name;
    private Date date;
    private String type;
    private Integer competitors;
    @ManyToOne
    @JoinColumn(name = "winner_player_id")
    private Player winner;
    @OneToMany(mappedBy = "tournament")
    private List<TournamentPhase> phases;

    public Tournament() {
        this.phases = new ArrayList<>();
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getCompetitors() {
        return competitors;
    }

    public void setCompetitors(Integer competitors) {
        this.competitors = competitors;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<TournamentPhase> getPhases() {
        return phases;
    }

    public void setPhases(List<TournamentPhase> phases) {
        this.phases = phases;
    }

    public void addPhase(TournamentPhase phase) {
        this.phases.add(phase);
    }


}
