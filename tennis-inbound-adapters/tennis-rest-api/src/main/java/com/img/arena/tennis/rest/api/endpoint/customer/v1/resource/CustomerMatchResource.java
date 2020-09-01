package com.img.arena.tennis.rest.api.endpoint.customer.v1.resource;


import com.img.arena.tennis.core.port.CustomerMatchService;
import com.img.arena.tennis.datastore.model.CustomerId;
import com.img.arena.tennis.datastore.model.Match;
import com.img.arena.tennis.rest.api.endpoint.customer.v1.response.CustomerMatchResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;
import java.util.stream.Stream;

@RestController
@RequestMapping("/v1/customer/my-matches")
public class CustomerMatchResource {

    private static final String CUSTOMER_ID = "customer-id";
    private static final String SUMMARY_TYPE = "summaryType";

    private final CustomerMatchService customerMatchService;
    private final MatchToCustomerMatchResponse matchToCustomerMatchResponse;
    private final SummaryTypeFormatter summaryTypeFormatter;

    public CustomerMatchResource(CustomerMatchService customerMatchService,
                                 MatchToCustomerMatchResponse matchToCustomerMatchResponse,
                                 SummaryTypeFormatter summaryTypeFormatter) {
        this.customerMatchService = customerMatchService;
        this.matchToCustomerMatchResponse = matchToCustomerMatchResponse;
        this.summaryTypeFormatter = summaryTypeFormatter;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Stream<CustomerMatchResponse> findCustomerMatches(@RequestHeader(CUSTOMER_ID) CustomerId customerId,
                                                             @RequestParam(SUMMARY_TYPE) String summaryType) {
        Function<Match, String> summaryFormatter = summaryTypeFormatter.find(summaryType);
        return customerMatchService.findCustomerMatches(customerId)
                                   .map(match -> matchToCustomerMatchResponse.toResponse(match, summaryFormatter));
    }
}
