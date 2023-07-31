package com.ecommerceservice.application.controller;

import com.ecommerceservice.application.request.CreateUserRequest;
import com.ecommerceservice.application.request.UpdateUserRequest;
import com.ecommerceservice.application.response.BasicResponse;
import com.ecommerceservice.application.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    @PostMapping(CREATE_USER)
    public BasicResponse create(@Valid @RequestBody CreateUserRequest request) {
        return userService.create(request);
    }

    @PutMapping(UPDATE_USER)
    public BasicResponse update(@Valid @RequestBody UpdateUserRequest request, Authentication authentication) {
        return userService.update(request, authentication);
    }

}
