package com.ecommerceservice.application.request;

import com.ecommerceservice.application.entity.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
public class UpdateItemRequest {

    @NotBlank(message = "UUID is required")
    @UUID(message = "Invalid UUID value")
    private String id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Quantity is required")
    private Integer quantity;

    @NotNull(message = "Price is required")
    private Float price;

    @NotNull(message = "Discount is required")
    private Integer discount;

    public Item toEntity(Item item) {
        item.setName(getName());
        item.setDescription(getDescription());
        item.setPrice(getPrice());
        item.setQuantity(getQuantity());
        item.setDiscount(getDiscount());
        return item;
    }
}
