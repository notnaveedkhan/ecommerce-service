package com.ecommerceservice.application.request;

import com.ecommerceservice.application.entity.Country;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateCountryRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Code is required")
    private String code;

    public Country toEntity() {
        Country country = new Country();
        country.setName(getName());
        country.setCode(getCode());
        return country;
    }

}
