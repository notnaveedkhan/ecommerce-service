package com.ecommerceservice.application.controller;

import com.ecommerceservice.application.request.CreateCountryRequest;
import com.ecommerceservice.application.request.UpdateCountryRequest;
import com.ecommerceservice.application.response.BasicResponse;
import com.ecommerceservice.application.response.CountryResponse;
import com.ecommerceservice.application.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CountryController extends BaseController {

    private final CountryService countryService;

    @PostMapping(CREATE_COUNTRY)
    public BasicResponse create(@Valid @RequestBody CreateCountryRequest request) {
        return countryService.create(request);
    }

    @PutMapping(UPDATE_COUNTRY)
    public BasicResponse update(@Valid @RequestBody UpdateCountryRequest request) {
        return countryService.update(request);
    }

    @GetMapping(COUNTRY_LIST)
    public List<CountryResponse> list() {
        return countryService.list();
    }

    @DeleteMapping(DELETE_COUNTRY)
    public BasicResponse delete(@PathVariable String uuid) {
        return countryService.delete(UUID.fromString(uuid));
    }

}
