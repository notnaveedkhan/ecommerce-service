package com.ecommerceservice.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Objects;
import java.util.UUID;

import static com.ecommerceservice.application.utility.Util.mask;

@Getter
@Setter
@ToString
@Entity(name = "_CARD")
public class Card extends AbstractPersistable<UUID> {

    @ToString.Exclude
    private String number;

    private String expiry;

    private String type;

    @ToString.Exclude
    private String code;

    private String name;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Card card) {
            return getId() != null && Objects.equals(getId(), card.getId());
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    @ToString.Include
    public String maskNumber() {
        return mask(number, 4);
    }

}
