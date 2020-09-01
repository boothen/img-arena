package com.img.arena.tennis.datastore.model;

import java.io.Serializable;
import java.util.Objects;

public class CustomerId implements Serializable {

    private static final long serialVersionUID = -5885379805332835942L;

    private Long customerId;

    protected CustomerId() {
        // Required by JPA
    }

    public CustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public static CustomerId valueOf(String id) {
        return new CustomerId(Long.valueOf(id));
    }

    public static CustomerId valueOf(Long id) {
        return new CustomerId(id);
    }

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
        CustomerId that = (CustomerId) o;
        return Objects.equals(customerId, that.customerId);
    }

    @Override
    public String toString() {
        return customerId.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}
