package com.img.arena.tennis.datastore.repository;

import com.img.arena.tennis.datastore.model.License;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface CustomerLicenseRepository extends Repository<License, Long> {

    List<License> findByCustomerId(Long customerId);

}
