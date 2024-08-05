package com.geo.challenge.model;

import jakarta.persistence.*;

@Entity
@Table(name= "tournament_phase_game")
public class TournamentPhaseGame {

    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer gameId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "player_id")
    private Player winner;

    @ManyToOne
    @JoinColumn(referencedColumnName = "player_id")
    private Player loser;

    @ManyToOne
    @JoinColumn(referencedColumnName = "phase_id")
    private TournamentPhase phase;

    public TournamentPhaseGame() { }

    public TournamentPhaseGame(Integer gameId, Player winner, Player loser, TournamentPhase phase) {
        this.gameId = gameId;
        this.winner = winner;
        this.loser = loser;
        this.phase = phase;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getLoser() {
        return loser;
    }

    public void setLoser(Player loser) {
        this.loser = loser;
    }

    public TournamentPhase getPhase() {
        return phase;
    }

    public void setPhase(TournamentPhase phase) {
        this.phase = phase;
    }
}
