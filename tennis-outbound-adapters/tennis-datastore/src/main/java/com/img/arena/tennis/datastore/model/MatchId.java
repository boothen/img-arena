package com.img.arena.tennis.datastore.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class MatchId implements Serializable {

    private static final long serialVersionUID = 7791709057339159147L;

    private UUID id;

    public MatchId() {
        id = UUID.randomUUID();
    }

    public MatchId(UUID id) {
        this.id = id;
    }

    public static MatchId valueOf(String id) {
        return new MatchId(UUID.fromString(id));
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
        MatchId that = (MatchId) o;
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
