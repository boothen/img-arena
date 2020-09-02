package com.img.arena.tennis.rest.api.endpoint.customer.v1.resource;

import com.img.arena.tennis.datastore.model.Match;

import java.util.function.Function;

public class NullSummaryFormatter implements Function<Match, String> {

    @Override
    public String apply(Match match) {
        return null;
    }
}
