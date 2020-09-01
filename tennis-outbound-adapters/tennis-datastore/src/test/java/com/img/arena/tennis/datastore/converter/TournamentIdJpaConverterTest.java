package com.img.arena.tennis.datastore.converter;

import com.img.arena.tennis.datastore.model.TournamentId;

import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class TournamentIdJpaConverterTest {

    private final TournamentIdJpaConverter tournamentIdJpaConverter = new TournamentIdJpaConverter();

    @Test
    public void shouldConvertTournamentIdToUUID() {
        UUID tournamentId = UUID.randomUUID();
        UUID value = tournamentIdJpaConverter.convertToDatabaseColumn(new TournamentId(tournamentId));
        assertThat(value).isEqualTo(tournamentId);
    }

    @Test
    public void shouldHandleNullTournamentId() {
        UUID value = tournamentIdJpaConverter.convertToDatabaseColumn(null);
        assertThat(value).isNull();
    }

    @Test
    public void shouldConvertUUIDToTournamentId() {
        UUID uuid = UUID.randomUUID();
        TournamentId tournamentId = tournamentIdJpaConverter.convertToEntityAttribute(uuid);
        assertThat(tournamentId).isEqualTo(new TournamentId(uuid));
    }

    @Test
    public void shouldHandleNullString() {
        TournamentId TournamentId = tournamentIdJpaConverter.convertToEntityAttribute(null);
        assertThat(TournamentId).isNull();
    }
}