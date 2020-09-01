package com.img.arena.tennis.rest.api.endpoint.customer.v1.resource;


import com.img.arena.tennis.datastore.model.Match;

import java.util.function.Function;

public class AvBSummaryFormatter implements Function<Match, String> {

    @Override
    public String apply(Match match) {
        return String.format("%s vs %s", match.getPlayerA(), match.getPlayerB());
    }
}
