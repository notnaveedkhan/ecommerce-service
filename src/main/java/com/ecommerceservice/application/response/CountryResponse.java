package com.ecommerceservice.application.response;

import com.ecommerceservice.application.entity.Country;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class CountryResponse {

    private String id;
    private String name;
    private String code;

    public CountryResponse(Country country) {
        setId(Objects.requireNonNull(country.getId()).toString());
        setName(country.getName());
        setCode(country.getCode());
    }
}
