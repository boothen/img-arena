package com.img.arena.tennis.rest.api.endpoint.customer.v1.resource;

import com.img.arena.tennis.datastore.model.Match;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class NullSummaryFormatterTest {

    private final NullSummaryFormatter nullSummaryFormatter = new NullSummaryFormatter();
    @Test
    public void shouldReturnNullSummary() {
        String summary = nullSummaryFormatter.apply(mock(Match.class));
        assertThat(summary).isNull();
    }
}