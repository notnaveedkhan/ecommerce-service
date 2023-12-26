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
@Entity(name = "_COUNTRY")
public class Country extends AbstractPersistable<UUID> {

    private String name;

    private String code;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Country country) {
            return getId() != null && Objects.equals(getId(), country.getId());
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

}
