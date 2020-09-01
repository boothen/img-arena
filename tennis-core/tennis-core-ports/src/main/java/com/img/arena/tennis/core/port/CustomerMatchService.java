package com.img.arena.tennis.core.port;

import com.img.arena.tennis.datastore.model.CustomerId;
import com.img.arena.tennis.datastore.model.Match;

import java.util.stream.Stream;

public interface CustomerMatchService {

    Stream<Match> findCustomerMatches(CustomerId customerId);
}
