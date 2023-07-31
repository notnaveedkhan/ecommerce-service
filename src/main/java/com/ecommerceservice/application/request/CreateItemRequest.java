package com.ecommerceservice.application.request;

import com.ecommerceservice.application.entity.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateItemRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    private Float price;

    @NotNull(message = "Quantity is required")
    private Integer quantity;

    @NotNull(message = "Discount is required")
    private Integer discount;

    public Item toEntity() {
        Item item = new Item();
        item.setName(getName());
        item.setDescription(getDescription());
        item.setPrice(getPrice());
        item.setQuantity(getQuantity());
        item.setDiscount(getDiscount());
        return item;
    }
}
