package com.img.arena.tennis.rest.api.endpoint.customer.v1.resource;


import com.img.arena.tennis.datastore.model.Match;
import com.img.arena.tennis.rest.api.endpoint.customer.v1.response.CustomerMatchResponse;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MatchToCustomerMatchResponse {

    public CustomerMatchResponse toResponse(Match match, Function<Match, String> summaryType) {
        String summary = summaryType.apply(match);
        return new CustomerMatchResponse(match.getId(),
                                         match.getStartDate(),
                                         match.getPlayerA(),
                                         match.getPlayerB(),
                                         summary);
    }
}
