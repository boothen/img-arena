package com.img.arena.tennis.rest.api.endpoint.customer.v1.resource;

import com.img.arena.tennis.datastore.model.Match;

import org.junit.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AvBTimeSummaryFormatterTest {

    private final DateTimeProvider dateTimeProvider = mock(DateTimeProvider.class);
    private final AvBTimeSummaryFormatter avBTimeSummaryFormatter = new AvBTimeSummaryFormatter(dateTimeProvider);

    @Test
    public void shouldReturnStartsInWhenStartIsAfterCurrentTime() {
        Match match = mock(Match.class);

        when(dateTimeProvider.currentZonedDateTime()).thenReturn(ZonedDateTime.parse("2020-08-31T12:00:00+01:00"));
        when(match.getStartDate()).thenReturn(ZonedDateTime.parse("2020-08-31T13:00:00+01:00"));
        when(match.getPlayerA()).thenReturn("Player 1");
        when(match.getPlayerB()).thenReturn("Player 2");

        String summary = avBTimeSummaryFormatter.apply(match);

        assertThat(summary).isEqualTo("Player 1 vs Player 2, starts in 60 minutes");
    }

    @Test
    public void shouldReturnStartedWhenStartIsBeforeCurrentTime() {
        Match match = mock(Match.class);

        when(dateTimeProvider.currentZonedDateTime()).thenReturn(ZonedDateTime.parse("2020-08-31T12:00:00+01:00"));
        when(match.getStartDate()).thenReturn(ZonedDateTime.parse("2020-08-31T11:55:00+01:00"));
        when(match.getPlayerA()).thenReturn("Player 1");
        when(match.getPlayerB()).thenReturn("Player 2");

        String summary = avBTimeSummaryFormatter.apply(match);

        assertThat(summary).isEqualTo("Player 1 vs Player 2, started 5 minutes ago");
    }

    @Test
    public void shouldReturnStartsIn0MinutesWhenLessThanAMinuteBetweenTimes() {
        Match match = mock(Match.class);

        when(dateTimeProvider.currentZonedDateTime()).thenReturn(ZonedDateTime.parse("2020-08-31T12:00:00+01:00"));
        when(match.getStartDate()).thenReturn(ZonedDateTime.parse("2020-08-31T12:00:30+01:00"));
        when(match.getPlayerA()).thenReturn("Player 1");
        when(match.getPlayerB()).thenReturn("Player 2");

        String summary = avBTimeSummaryFormatter.apply(match);

        assertThat(summary).isEqualTo("Player 1 vs Player 2, starts in 0 minutes");
    }

    @Test
    public void shouldReturnStarted0MinutesWhenLessThanAMinuteBetweenTimes() {
        Match match = mock(Match.class);

        when(dateTimeProvider.currentZonedDateTime()).thenReturn(ZonedDateTime.parse("2020-08-31T12:00:00+01:00"));
        when(match.getStartDate()).thenReturn(ZonedDateTime.parse("2020-08-31T11:59:30+01:00"));
        when(match.getPlayerA()).thenReturn("Player 1");
        when(match.getPlayerB()).thenReturn("Player 2");

        String summary = avBTimeSummaryFormatter.apply(match);

        assertThat(summary).isEqualTo("Player 1 vs Player 2, started 0 minutes ago");
    }
}