package com.geo.challenge.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tournament_phase")
public class TournamentPhase {

    @Id
    @Column(name = "phase_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer phaseId;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    private Integer phaseNumber;

    @OneToMany(mappedBy = "phase")
    private List<TournamentPhaseGame> games;

    public TournamentPhase() {
        this.games = new ArrayList<>();
    }

    public Integer getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Integer phaseId) {
        this.phaseId = phaseId;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Integer getPhaseNumber() {
        return phaseNumber;
    }

    public void setPhaseNumber(Integer phaseNumber) {
        this.phaseNumber = phaseNumber;
    }

    public List<TournamentPhaseGame> getGames() {
        return games;
    }

    public void setGames(List<TournamentPhaseGame> games) {
        this.games = games;
    }

    public void addGame(TournamentPhaseGame game) {
        this.games.add(game);
    }

}
