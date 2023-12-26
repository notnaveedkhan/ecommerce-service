package com.ecommerceservice.application.request;

import com.ecommerceservice.application.entity.Card;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
public class UpdateCardRequest {

    @NotBlank(message = "Card uuid is required")
    @UUID(message = "Invalid card uuid value")
    private String id;
    @NotBlank(message = "Number is required")
    @Pattern(regexp = "[0-9]{16}", message = "Invalid card number")
    private String number;
    @NotBlank(message = "Expiry is required")
    private String expiry;
    @NotBlank(message = "Type is required")
    private String type;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Code is required")
    private String code;

    public Card toEntity(Card card) {
        card.setNumber(getNumber());
        card.setExpiry(getExpiry());
        card.setType(getType());
        card.setName(getName());
        card.setCode(getCode());
        return card;
    }

}
