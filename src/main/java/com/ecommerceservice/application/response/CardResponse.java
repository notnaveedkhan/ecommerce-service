package com.ecommerceservice.application.response;

import com.ecommerceservice.application.entity.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

import static com.ecommerceservice.application.utility.Util.mask;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CardResponse {

    private String id;

    @ToString.Exclude
    private String number;

    private String expiry;

    private String type;

    @ToString.Exclude
    private String code;

    private String name;

    @ToString.Include
    public String getNumber() {
        return mask(number, 4);
    }

    public CardResponse(Card card) {
        setId(Objects.requireNonNull(card.getId()).toString());
        setNumber(card.getNumber());
        setExpiry(card.getExpiry());
        setType(card.getType());
        setCode(card.getCode());
        setName(card.getName());
    }
}
