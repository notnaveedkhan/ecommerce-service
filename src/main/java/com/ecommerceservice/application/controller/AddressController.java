package com.ecommerceservice.application.controller;

import com.ecommerceservice.application.entity.User;
import com.ecommerceservice.application.request.CreateAddressRequest;
import com.ecommerceservice.application.request.UpdateAddressRequest;
import com.ecommerceservice.application.response.AddressResponse;
import com.ecommerceservice.application.response.BasicResponse;
import com.ecommerceservice.application.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AddressController extends BaseController {

    private final AddressService addressService;

    @PostMapping(CREATE_ADDRESS)
    public BasicResponse create(@Valid @RequestBody CreateAddressRequest request, Authentication authentication) {
        return addressService.create(request, (User) authentication.getPrincipal());
    }

    @PutMapping(UPDATE_ADDRESS)
    public BasicResponse update(@Valid @RequestBody UpdateAddressRequest request) {
        return addressService.update(request);
    }

    @GetMapping(ADDRESS_LIST)
    public List<AddressResponse> list(Authentication authentication) {
        return addressService.list((User) authentication.getPrincipal());
    }

    @DeleteMapping(DELETE_ADDRESS)
    public BasicResponse delete(@PathVariable String uuid) {
        return addressService.delete(UUID.fromString(uuid));
    }

}
