package com.geo.challenge.model;

import jakarta.persistence.*;

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

    public TournamentPhase() { }

    public TournamentPhase(Integer phaseId, Tournament tournament, Integer phaseNumber) {
        this.phaseId = phaseId;
        this.tournament = tournament;
        this.phaseNumber = phaseNumber;
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
}
