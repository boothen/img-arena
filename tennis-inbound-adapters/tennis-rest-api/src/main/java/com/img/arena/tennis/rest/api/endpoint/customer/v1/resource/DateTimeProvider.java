package com.img.arena.tennis.rest.api.endpoint.customer.v1.resource;

import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class DateTimeProvider {

    public ZonedDateTime currentZonedDateTime() {
        return ZonedDateTime.now();
    }
}
