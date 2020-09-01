package com.img.arena.tennis.datastore.model;

import java.io.Serializable;
import java.util.stream.Stream;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@DiscriminatorValue("TOURNAMENT")
@Entity
public class TournamentLicense extends License implements Serializable {

    private static final long serialVersionUID = 331044183501334067L;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "licensed_id", insertable=false, nullable = false, updatable = false)
    private Tournament tournament;

    @Override
    public Stream<Match> matches() {
        return tournament.matches();
    }
}
