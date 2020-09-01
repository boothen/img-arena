package com.img.arena.tennis.rest.api.endpoint.customer.v1.resource;

import com.img.arena.tennis.datastore.model.Match;
import com.img.arena.tennis.datastore.model.MatchId;
import com.img.arena.tennis.rest.api.endpoint.customer.v1.response.CustomerMatchResponse;

import org.junit.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchToCustomerMatchResponseTest {

    private final MatchToCustomerMatchResponse matchToCustomerMatchResponse = new MatchToCustomerMatchResponse();

    @Test
    public void shouldReturnCustomerMatchResponse() {
        MatchId matchId = new MatchId();

        Match match = new Match(matchId,
                                ZonedDateTime.parse("2020-08-31T12:00:00+01:00"),
                                "Player 1",
                                "Player 2");
        CustomerMatchResponse customerMatchResponse = matchToCustomerMatchResponse.toResponse(match, a -> "formatted summary");

        assertThat(customerMatchResponse.getMatchId()).isEqualTo(matchId);
        assertThat(customerMatchResponse.getStartDate()).isEqualTo(ZonedDateTime.parse("2020-08-31T12:00:00+01:00"));
        assertThat(customerMatchResponse.getPlayerA()).isEqualTo("Player 1");
        assertThat(customerMatchResponse.getPlayerB()).isEqualTo("Player 2");
        assertThat(customerMatchResponse.getSummary()).isEqualTo("formatted summary");
    }
}