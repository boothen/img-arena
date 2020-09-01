package com.img.arena.tennis.datastore.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class LicenseId implements Serializable {

    private static final long serialVersionUID = 2733872292852414110L;

    private Long customerId;
    private UUID licensedId;

    protected LicenseId() {
        // Required by JPA
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LicenseId licenseId = (LicenseId) o;
        return customerId.equals(licenseId.customerId) &&
               licensedId.equals(licenseId.licensedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, licensedId);
    }
}
