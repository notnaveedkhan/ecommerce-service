package com.ecommerceservice.application.service;

import com.ecommerceservice.application.entity.Country;
import com.ecommerceservice.application.exception.InvalidRequestException;
import com.ecommerceservice.application.repository.CountryRepository;
import com.ecommerceservice.application.request.CreateCountryRequest;
import com.ecommerceservice.application.request.UpdateCountryRequest;
import com.ecommerceservice.application.response.BasicResponse;
import com.ecommerceservice.application.response.CountryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.ecommerceservice.application.common.CommonText.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public BasicResponse create(CreateCountryRequest request) {
        log.info(">>> CountryService.create - request: {}", request);
        if (countryRepository.existsByCode(request.getCode())) {
            log.info("<<< CountryService.create - country already exists");
            throw new InvalidRequestException(HttpStatus.CONFLICT, COUNTRY_ALREADY_EXISTS);
        }
        Country country = request.toEntity();
        countryRepository.save(country);
        log.info("<<< CountryService.create - country: {}", country);
        return new BasicResponse(SUCCESS, COUNTRY_CREATED);
    }

    public BasicResponse update(UpdateCountryRequest request) {
        log.info(">>> CountryService.update - request: {}", request);
        Country country = countryRepository.findById(UUID.fromString(request.getId())).orElseThrow(() -> {
            log.info("<<< CountryService.update - country not found");
            return new InvalidRequestException(HttpStatus.NOT_FOUND, COUNTRY_NOT_FOUND);
        });
        country = request.toEntity(country);
        countryRepository.save(country);
        log.info("<<< CountryService.update - country: {}", country);
        return new BasicResponse(SUCCESS, COUNTRY_UPDATED);
    }

    public List<CountryResponse> list() {
        log.info(">>> CountryService.list");
        List<Country> countries = countryRepository.findAll();
        log.info("<<< CountryService.list - countries: {}", countries.size());
        return countries.stream()
                .map(CountryResponse::new)
                .toList();
    }

    public BasicResponse delete(UUID uuid) {
        log.info(">>> CountryService.delete - uuid: {}", uuid);
        countryRepository.deleteById(uuid);
        log.info("<<< CountryService.delete - uuid: {}", uuid);
        return new BasicResponse(SUCCESS, COUNTRY_DELETED);
    }
}
