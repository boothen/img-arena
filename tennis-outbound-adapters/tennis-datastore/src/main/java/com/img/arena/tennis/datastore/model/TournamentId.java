package com.img.arena.tennis.datastore.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class TournamentId implements Serializable {

    private static final long serialVersionUID = 8187579191327456377L;

    private UUID id;

    public TournamentId() {
        id = UUID.randomUUID();
    }

    public TournamentId(UUID id) {
        this.id = id;
    }

    public static TournamentId valueOf(String id) {
        return new TournamentId(UUID.fromString(id));
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TournamentId that = (TournamentId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
