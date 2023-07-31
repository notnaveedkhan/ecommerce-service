package com.ecommerceservice.application.response;

import com.ecommerceservice.application.entity.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AddressResponse {

    private String id;
    private String countryName;
    private String countryCode;
    private String streetAddress;
    private String state;
    private String city;
    private String zipCode;

    public AddressResponse(Address address) {
        setId(Objects.requireNonNull(address.getId()).toString());
        setCountryName(address.getCountry().getName());
        setCountryCode(address.getCountry().getCode());
        setStreetAddress(address.getStreetAddress());
        setState(address.getState());
        setCity(address.getCity());
        setZipCode(address.getZipCode());
    }
}
