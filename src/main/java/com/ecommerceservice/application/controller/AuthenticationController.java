package com.ecommerceservice.application.controller;

import com.ecommerceservice.application.request.AuthenticationRequest;
import com.ecommerceservice.application.response.AuthenticationResponse;
import com.ecommerceservice.application.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController extends BaseController {

    private final AuthenticationService authenticationService;

    @PostMapping(AUTHENTICATE_USER)
    public AuthenticationResponse authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }

}
