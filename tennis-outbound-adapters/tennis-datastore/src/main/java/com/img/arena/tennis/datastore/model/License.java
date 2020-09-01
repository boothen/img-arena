package com.img.arena.tennis.datastore.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "license")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "license_type",
    length = 25
)
@Access(AccessType.FIELD)
@IdClass(LicenseId.class)
public abstract class License implements Serializable {

    private static final long serialVersionUID = -2923027364557198558L;

    @Id
    @Column(name = "customer_id", nullable = false, updatable = false)
    private Long customerId;

    @Id
    @Column(name = "licensed_id", nullable = false, updatable = false)
    private UUID licensedId;

    public abstract Stream<Match> matches();

    public Long getCustomerId() {
        return customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        License license = (License) o;
        return customerId.equals(license.customerId) &&
               licensedId.equals(license.licensedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, licensedId);
    }
}
