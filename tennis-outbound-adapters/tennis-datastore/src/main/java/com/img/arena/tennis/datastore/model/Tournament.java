package com.img.arena.tennis.datastore.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tournament")
@Access(AccessType.FIELD)
//@IdClass(TournamentId.class)
public class Tournament implements Serializable {

    private static final long serialVersionUID = -8682510251594567825L;

    @Id
    @Column(name = "tournament_id", nullable = false, updatable = false, columnDefinition = "uuid")
    private UUID id;

    @OneToMany(mappedBy = "tournamentId")
    private List<Match> matches;

    public TournamentId getId() {
        return new TournamentId(id);
    }

    public Stream<Match> matches() {
        return matches.stream();
    }
}
