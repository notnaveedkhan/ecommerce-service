package com.ecommerceservice.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "_ADDRESS")
public class Address extends AbstractPersistable<UUID> {

    private String streetAddress;
    private String state;
    private String city;
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Country country;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Address address) {
            return getId() != null && Objects.equals(getId(), address.getId());
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

}
