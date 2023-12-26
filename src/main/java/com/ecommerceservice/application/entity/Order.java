package com.ecommerceservice.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "_ORDER")
public class Order extends AbstractPersistable<UUID> {

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private Long totalAmount;

    private LocalDateTime orderedAt;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Order order) {
            return getId() != null && Objects.equals(getId(), order.getId());
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

}
