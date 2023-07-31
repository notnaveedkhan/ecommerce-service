package com.ecommerceservice.application.request;

import com.ecommerceservice.application.entity.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@ToString
public class CreateAddressRequest {

    @NotBlank(message = "Country is required")
    @UUID(message = "Invalid UUID value")
    private String country;

    @NotBlank(message = "Street address is required")
    private String streetAddress;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "City is required")
    private String city;

    private String zipCode;
    public Address toEntity() {
        Address address = new Address();
        address.setStreetAddress(getStreetAddress());
        address.setState(getState());
        address.setCity(getCity());
        address.setZipCode(getZipCode());
        return address;
    }

}
