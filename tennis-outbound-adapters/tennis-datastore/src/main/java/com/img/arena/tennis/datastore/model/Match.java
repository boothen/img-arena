package com.img.arena.tennis.datastore.model;

import com.img.arena.tennis.datastore.converter.TournamentIdJpaConverter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "match")
@Access(AccessType.FIELD)
public class Match implements Serializable {

    private static final long serialVersionUID = 5379115575120064415L;

    @Id
    @Column(name = "match_id", nullable = false, updatable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "start_date", nullable = false, updatable = false)
    private ZonedDateTime startDate;

    @Column(name = "player_a", nullable = false, updatable = false)
    private String playerA;

    @Column(name = "player_b", nullable = false, updatable = false)
    private String playerB;

    @Column(name = "tournament_id", nullable = false, updatable = false)
    @Convert(converter = TournamentIdJpaConverter.class)
    private TournamentId tournamentId;

    public Match() {
        // Required by JPA
    }

    public Match(MatchId id, ZonedDateTime startDate, String playerA, String playerB) {
        this.id = id.getId();
        this.startDate = startDate;
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public MatchId getId() {
        return new MatchId(id);
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public String getPlayerA() {
        return playerA;
    }

    public String getPlayerB() {
        return playerB;
    }

}
