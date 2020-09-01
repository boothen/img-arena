package com.img.arena.tennis.datastore.model;

import java.io.Serializable;
import java.util.stream.Stream;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@DiscriminatorValue("MATCH")
@Entity
public class MatchLicense extends License implements Serializable {

    private static final long serialVersionUID = 3048968331486786695L;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "licensed_id", insertable=false, nullable = false, updatable = false)
    private Match match;

    public Match getMatch() {
        return match;
    }

    @Override
    public Stream<Match> matches() {
        return Stream.of(match);
    }
}
