package com.img.arena.tennis.core.impl;

import com.img.arena.tennis.core.port.CustomerMatchService;
import com.img.arena.tennis.datastore.model.CustomerId;
import com.img.arena.tennis.datastore.repository.CustomerLicenseRepository;
import com.img.arena.tennis.datastore.model.License;
import com.img.arena.tennis.datastore.model.Match;

import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class CustomerMatchServiceImpl implements CustomerMatchService {

    private final CustomerLicenseRepository customerLicenseRepository;

    public CustomerMatchServiceImpl(CustomerLicenseRepository customerLicenseRepository) {
        this.customerLicenseRepository = customerLicenseRepository;
    }

    @Override
    public Stream<Match> findCustomerMatches(CustomerId customerId) {
        return customerLicenseRepository.findByCustomerId(customerId.getCustomerId())
                                        .stream()
                                        .flatMap(License::matches);
    }
}
