package com.img.arena.tennis.rest.api.endpoint.customer.v1.resource;

import org.junit.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeProviderTest {

    private final DateTimeProvider dateTimeProvider = new DateTimeProvider();

    @Test
    public void shouldReturnNow() {
        ZonedDateTime zonedDateTime = dateTimeProvider.currentZonedDateTime();
        assertThat(zonedDateTime).isEqualToIgnoringSeconds(zonedDateTime);
    }
}