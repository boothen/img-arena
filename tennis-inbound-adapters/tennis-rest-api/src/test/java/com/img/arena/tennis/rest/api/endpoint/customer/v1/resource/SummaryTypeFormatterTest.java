package com.img.arena.tennis.rest.api.endpoint.customer.v1.resource;

import com.img.arena.tennis.datastore.model.Match;

import org.junit.Test;

import java.util.Map;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.mock;

public class SummaryTypeFormatterTest {

    @SuppressWarnings("unchecked")
    private final Function<Match, String> function = mock(Function.class);
    private final Map<String, Function<Match, String>> summaryFormatters = Map.of("AvB", function);
    private final SummaryTypeFormatter summaryTypeFormatter = new SummaryTypeFormatter(summaryFormatters);


    @Test
    public void shouldReturnSummaryFormatterWhenSummaryTypeIsValid() {
        Function<Match, String> avBFunction = summaryTypeFormatter.find("AvB");

        assertThat(avBFunction).isEqualTo(function);
    }

    @Test
    public void shouldThrowExceptionWhenSummaryTypeIsInvalid() {
        assertThatIllegalArgumentException().isThrownBy(() -> summaryTypeFormatter.find("AvB2"));
    }
}