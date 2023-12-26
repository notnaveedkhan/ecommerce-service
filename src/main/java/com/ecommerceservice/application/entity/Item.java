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
@Entity(name = "_ITEM")
public class Item extends AbstractPersistable<UUID> {

    private String name;

    private String description;

    private Float price;

    private Integer discount;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    private User seller;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Item item) {
            return getId() != null && Objects.equals(getId(), item.getId());
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

}
