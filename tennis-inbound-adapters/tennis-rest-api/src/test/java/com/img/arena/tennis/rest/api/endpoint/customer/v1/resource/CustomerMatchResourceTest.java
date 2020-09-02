package com.img.arena.tennis.rest.api.endpoint.customer.v1.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.img.arena.tennis.core.port.CustomerMatchService;
import com.img.arena.tennis.datastore.model.CustomerId;
import com.img.arena.tennis.datastore.model.Match;
import com.img.arena.tennis.datastore.model.MatchId;
import com.img.arena.tennis.rest.api.config.Application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.UUID;
import java.util.stream.Stream;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
public class CustomerMatchResourceTest {

    private static final String V_1_CUSTOMER_MY_MATCHES = "/v1/customer/my-matches";

    private static final DateTimeFormatter ISO_OFFSET_DATE_TIME_WITHOUT_NANOS = new DateTimeFormatterBuilder().parseCaseInsensitive()
                                                                                                              .append(ISO_LOCAL_DATE)
                                                                                                              .appendLiteral('T').appendValue(HOUR_OF_DAY, 2)
                                                                                                              .appendLiteral(':')
                                                                                                              .appendValue(MINUTE_OF_HOUR, 2)
                                                                                                              .optionalStart()
                                                                                                              .appendLiteral(':')
                                                                                                              .appendValue(SECOND_OF_MINUTE, 2)
                                                                                                              .parseLenient()
                                                                                                              .appendOffsetId()
                                                                                                              .parseStrict()
                                                                                                              .toFormatter();

    @MockBean
    private CustomerMatchService customerMatchService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnAvBSummaryWhenAvBSummaryTypeProvided() throws Exception {
        MatchId matchId = new MatchId(UUID.fromString("ff1b1322-5570-4846-a421-e14acbcc4a2b"));
        Match match = new Match(matchId,
                                ZonedDateTime.parse("2020-08-31T12:00:00+01:00"),
                                "Player 1",
                                "Player 2");
        when(customerMatchService.findCustomerMatches(new CustomerId(123L))).thenReturn(Stream.of(match));

        mockMvc.perform(MockMvcRequestBuilders.get(V_1_CUSTOMER_MY_MATCHES)
                                              .header("customer-id", 123)
                                              .param("summaryType", "AvB"))
               .andExpect(status().is2xxSuccessful())
               .andExpect(content().json("[{\"matchId\":\"ff1b1322-5570-4846-a421-e14acbcc4a2b\","
                                         + "\"startDate\":\"2020-08-31T12:00:00+01:00\","
                                         + "\"playerA\":\"Player 1\","
                                         + "\"playerB\":\"Player 2\","
                                         + "\"summary\":\"Player 1 vs Player 2\""
                                         + "}]"));

    }

    @Test
    public void shouldReturnNoSummaryFieldWhenNoSummaryTypeParameterIsProvided() throws Exception {
        MatchId matchId = new MatchId(UUID.fromString("ff1b1322-5570-4846-a421-e14acbcc4a2b"));
        Match match = new Match(matchId,
                                ZonedDateTime.parse("2020-08-31T12:00:00+01:00"),
                                "Player 1",
                                "Player 2");
        when(customerMatchService.findCustomerMatches(new CustomerId(123L))).thenReturn(Stream.of(match));

        mockMvc.perform(MockMvcRequestBuilders.get(V_1_CUSTOMER_MY_MATCHES)
                                              .header("customer-id", 123))
               .andExpect(status().is2xxSuccessful())
               .andExpect(content().json("[{\"matchId\":\"ff1b1322-5570-4846-a421-e14acbcc4a2b\","
                                         + "\"startDate\":\"2020-08-31T12:00:00+01:00\","
                                         + "\"playerA\":\"Player 1\","
                                         + "\"playerB\":\"Player 2\""
                                         + "}]"));

    }

    @Test
    public void shouldReturnAvBTimeSummaryWithStartsInWhenAvBTimeSummaryTypeProvided() throws Exception {
        MatchId matchId = new MatchId(UUID.fromString("ff1b1322-5570-4846-a421-e14acbcc4a2b"));
        ZonedDateTime startDate = ZonedDateTime.now().plusMinutes(10).plusSeconds(10);
        Match match = new Match(matchId,
                                startDate,
                                "Player 1",
                                "Player 2");
        when(customerMatchService.findCustomerMatches(new CustomerId(123L))).thenReturn(Stream.of(match));
        String formattedStartDate = ISO_OFFSET_DATE_TIME_WITHOUT_NANOS.format(startDate);
        mockMvc.perform(MockMvcRequestBuilders.get(V_1_CUSTOMER_MY_MATCHES)
                                              .header("customer-id", 123)
                                              .param("summaryType", "AvBTime"))
               .andExpect(status().is2xxSuccessful())
               .andExpect(content().json("[{\"matchId\":\"ff1b1322-5570-4846-a421-e14acbcc4a2b\","
                                         + "\"startDate\":\"" + formattedStartDate + "\","
                                         + "\"playerA\":\"Player 1\","
                                         + "\"playerB\":\"Player 2\","
                                         + "\"summary\":\"Player 1 vs Player 2, starts in 10 minutes\""
                                         + "}]"));

    }

    @Test
    public void shouldReturnAvBTimeSummaryWithStartedWhenAvBTimeSummaryTypeProvided() throws Exception {
        MatchId matchId = new MatchId(UUID.fromString("ff1b1322-5570-4846-a421-e14acbcc4a2b"));
        ZonedDateTime startDate = ZonedDateTime.now().minusMinutes(10);
        Match match = new Match(matchId,
                                startDate,
                                "Player 1",
                                "Player 2");
        when(customerMatchService.findCustomerMatches(new CustomerId(123L))).thenReturn(Stream.of(match));
        String formattedStartDate = ISO_OFFSET_DATE_TIME_WITHOUT_NANOS.format(startDate);
        mockMvc.perform(MockMvcRequestBuilders.get(V_1_CUSTOMER_MY_MATCHES)
                                              .header("customer-id", 123)
                                              .param("summaryType", "AvBTime"))
               .andExpect(status().is2xxSuccessful())
               .andExpect(content().json("[{\"matchId\":\"ff1b1322-5570-4846-a421-e14acbcc4a2b\","
                                         + "\"startDate\":\"" + formattedStartDate + "\","
                                         + "\"playerA\":\"Player 1\","
                                         + "\"playerB\":\"Player 2\","
                                         + "\"summary\":\"Player 1 vs Player 2, started 10 minutes ago\""
                                         + "}]"));

    }

    @Test
    public void shouldThrowExceptionWhenCustomerIdHeaderIsMissing() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(V_1_CUSTOMER_MY_MATCHES)
                                              .param("summaryType", "AvB"))
               .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldThrowExceptionWhenSummaryTypeIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(V_1_CUSTOMER_MY_MATCHES)
                                              .param("summaryType", "Something")
                                              .header("customer-id", 123))
               .andExpect(status().isBadRequest());
    }
}
