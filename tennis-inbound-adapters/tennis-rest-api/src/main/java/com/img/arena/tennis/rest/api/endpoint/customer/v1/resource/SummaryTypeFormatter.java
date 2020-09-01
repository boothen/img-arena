package com.img.arena.tennis.rest.api.endpoint.customer.v1.resource;


import com.img.arena.tennis.datastore.model.Match;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
public class SummaryTypeFormatter {

    private final Map<String, Function<Match, String>> summaryFormatters;

    public SummaryTypeFormatter(Map<String, Function<Match, String>> summaryFormatters) {
        this.summaryFormatters = summaryFormatters;
    }

    public Function<Match, String> find(String summaryType) {
        Function<Match, String> summaryFormatter = summaryFormatters.get(summaryType);
        if (summaryFormatter == null) {
            throw new IllegalArgumentException("Invalid summaryType parameter");
        }
        return summaryFormatter;
    }
}
