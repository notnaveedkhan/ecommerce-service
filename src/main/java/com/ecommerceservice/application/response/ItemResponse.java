package com.ecommerceservice.application.response;

import com.ecommerceservice.application.entity.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class ItemResponse {

    private String id;
    private String name;
    private String description;
    private Float price;
    private Integer quantity;
    private Integer discount;

    public ItemResponse(Item item) {
        setId(Objects.requireNonNull(item.getId()).toString());
        setName(item.getName());
        setDescription(item.getDescription());
        setPrice(item.getPrice());
        setQuantity(item.getQuantity());
        setDiscount(item.getDiscount());
    }
}
