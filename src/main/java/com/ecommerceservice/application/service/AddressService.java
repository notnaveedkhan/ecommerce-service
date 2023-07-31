package com.ecommerceservice.application.service;

import com.ecommerceservice.application.entity.Address;
import com.ecommerceservice.application.entity.Country;
import com.ecommerceservice.application.entity.User;
import com.ecommerceservice.application.exception.InvalidRequestException;
import com.ecommerceservice.application.repository.AddressRepository;
import com.ecommerceservice.application.repository.CountryRepository;
import com.ecommerceservice.application.request.CreateAddressRequest;
import com.ecommerceservice.application.request.UpdateAddressRequest;
import com.ecommerceservice.application.response.AddressResponse;
import com.ecommerceservice.application.response.BasicResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.ecommerceservice.application.common.CommonText.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final CountryRepository countryRepository;

    public BasicResponse create(CreateAddressRequest request, User user) {
        log.info(">>> AddressService.create - request: {}", request);
        Country country = countryRepository.findById(UUID.fromString(request.getCountry())).orElseThrow(() -> {
            log.info("<<< AddressService.create - country not found");
            return new InvalidRequestException(HttpStatus.NOT_FOUND, COUNTRY_NOT_FOUND);
        });
        Address address = request.toEntity();
        address.setCountry(country);
        address.setUser(user);
        addressRepository.save(address);
        log.info("<<< AddressService.create - address: {}", address);
        return new BasicResponse(SUCCESS, ADDRESS_CREATED);
    }

    public BasicResponse update(UpdateAddressRequest request) {
        log.info(">>> AddressService.update - request: {}", request);
        Address address = addressRepository.findById(UUID.fromString(request.getId())).orElseThrow(() -> {
            log.info("<<< AddressService.update - address not found");
            return new InvalidRequestException(HttpStatus.NOT_FOUND, ADDRESS_NOT_FOUND);
        });
        address = request.toEntity(address);
        if (!Objects.equals(address.getCountry().getId(), UUID.fromString(request.getCountry()))) {
            Country country = countryRepository.findById(UUID.fromString(request.getCountry())).orElseThrow(() -> {
                log.info("<<< AddressService.update - country not found");
                return new InvalidRequestException(HttpStatus.NOT_FOUND, COUNTRY_NOT_FOUND);
            });
            address.setCountry(country);
        }
        addressRepository.save(address);
        log.info("<<< AddressService.update - address: {}", address);
        return new BasicResponse(SUCCESS, ADDRESS_UPDATED);
    }

    public List<AddressResponse> list(User user) {
        log.info(">>> AddressService.list");
        List<Address> addresses = addressRepository.findByUser(user);
        log.info("<<< AddressService.list - addresses: {}", addresses.size());
        return addresses.stream()
                .map(AddressResponse::new)
                .toList();
    }

    public BasicResponse delete(UUID uuid) {
        log.info(">>> AddressService.delete - uuid: {}", uuid);
        addressRepository.deleteById(uuid);
        log.info("<<< AddressService.delete - uuid: {}", uuid);
        return new BasicResponse(SUCCESS, ADDRESS_DELETED);
    }
}
