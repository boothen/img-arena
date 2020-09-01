package com.img.arena.tennis.rest.api.endpoint.customer.v1.response;


import com.img.arena.tennis.datastore.model.MatchId;

import java.time.ZonedDateTime;

public class CustomerMatchResponse {

    private final MatchId matchId;
    private final ZonedDateTime startDate;
    private final String playerA;
    private final String playerB;
    private final String summary;

    public CustomerMatchResponse(MatchId matchId,
                                 ZonedDateTime startDate,
                                 String playerA,
                                 String playerB,
                                 String summary) {
        this.matchId = matchId;
        this.startDate = startDate;
        this.playerA = playerA;
        this.playerB = playerB;
        this.summary = summary;
    }

    public MatchId getMatchId() {
        return matchId;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public String getPlayerA() {
        return playerA;
    }

    public String getPlayerB() {
        return playerB;
    }

    public String getSummary() {
        return summary;
    }
}
