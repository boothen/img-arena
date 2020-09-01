package com.img.arena.tennis.rest.api.endpoint.customer.v1.resource;

import com.img.arena.tennis.datastore.model.Match;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AvBSummaryFormatterTest {

    private final AvBSummaryFormatter avBSummaryFormatter = new AvBSummaryFormatter();

    @Test
    public void shouldReturnPlayerAvsPlayerB() {
        Match match = mock(Match.class);

        when(match.getPlayerA()).thenReturn("Player 1");
        when(match.getPlayerB()).thenReturn("Player 2");

        String summary = avBSummaryFormatter.apply(match);

        assertThat(summary).isEqualTo("Player 1 vs Player 2");
    }
}