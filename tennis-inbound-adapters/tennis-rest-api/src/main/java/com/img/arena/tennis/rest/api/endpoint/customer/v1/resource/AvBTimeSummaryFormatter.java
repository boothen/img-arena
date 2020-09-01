package com.img.arena.tennis.rest.api.endpoint.customer.v1.resource;


import com.img.arena.tennis.datastore.model.Match;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.function.Function;

public class AvBTimeSummaryFormatter implements Function<Match, String> {

    private final DateTimeProvider dateTimeProvider;

    public AvBTimeSummaryFormatter(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public String apply(Match match) {
        ZonedDateTime now = dateTimeProvider.currentZonedDateTime();
        // FIXME: There's an open question around what should be displayed if the time is less than a minute before
        // or after the match has started. Is starts in 0 minutes or started 0 minutes ago acceptable?
        if (match.getStartDate().isAfter(now)) {
            long minutes = calculateMinutes(now, match.getStartDate());
            return String.format("%s vs %s, starts in %d minutes", match.getPlayerA(), match.getPlayerB(), minutes);
        }
        long minutes = calculateMinutes(match.getStartDate(), now);
        return String.format("%s vs %s, started %d minutes ago", match.getPlayerA(), match.getPlayerB(), minutes);
    }

    private long calculateMinutes(ZonedDateTime startDateTime, ZonedDateTime endDateTime) {
        Duration time = Duration.between(startDateTime, endDateTime);
        return time.toMinutes();
    }
}
