package com.ecommerceservice.application.request;

import com.ecommerceservice.application.entity.Country;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@ToString
public class UpdateCountryRequest {

    @NotBlank(message = "Country uuid is required")
    @UUID(message = "Invalid Country uuid value")
    private String id;

    @NotBlank(message = "Name is required")
    private String name;

    public Country toEntity(Country country) {
        country.setName(getName());
        return country;
    }

}
