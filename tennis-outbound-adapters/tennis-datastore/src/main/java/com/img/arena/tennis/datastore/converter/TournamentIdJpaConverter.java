package com.img.arena.tennis.datastore.converter;

import com.img.arena.tennis.datastore.model.TournamentId;

import java.util.UUID;

import javax.persistence.AttributeConverter;

public class TournamentIdJpaConverter implements AttributeConverter<TournamentId, UUID> {

    @Override
    public UUID convertToDatabaseColumn(TournamentId tournamentId) {
        return tournamentId != null ? tournamentId.getId() : null;
    }

    @Override
    public TournamentId convertToEntityAttribute(UUID value) {
        return value != null ? new TournamentId(value) : null;
    }

}
