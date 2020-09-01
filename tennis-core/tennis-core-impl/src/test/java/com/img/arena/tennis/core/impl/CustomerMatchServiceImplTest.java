package com.img.arena.tennis.core.impl;

import com.img.arena.tennis.datastore.model.CustomerId;
import com.img.arena.tennis.datastore.model.License;
import com.img.arena.tennis.datastore.model.Match;
import com.img.arena.tennis.datastore.repository.CustomerLicenseRepository;

import org.junit.Test;

import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerMatchServiceImplTest {

    private final CustomerLicenseRepository customerLicenseRepository = mock(CustomerLicenseRepository.class);
    private final CustomerMatchServiceImpl customerMatchService = new CustomerMatchServiceImpl(customerLicenseRepository);

    @Test
    public void shouldFindCustomerMatches() {
        License license = mock(License.class);
        Match match = mock(Match.class);

        when(customerLicenseRepository.findByCustomerId(123L)).thenReturn(Collections.singletonList(license));
        when(license.matches()).thenReturn(Stream.of(match));

        Stream<Match> customerMatches = customerMatchService.findCustomerMatches(new CustomerId(123L));

        assertThat(customerMatches).contains(match);
    }
}