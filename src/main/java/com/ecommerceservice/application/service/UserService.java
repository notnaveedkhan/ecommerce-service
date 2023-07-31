package com.ecommerceservice.application.service;

import com.ecommerceservice.application.entity.Role;
import com.ecommerceservice.application.entity.User;
import com.ecommerceservice.application.exception.InvalidRequestException;
import com.ecommerceservice.application.repository.UserRepository;
import com.ecommerceservice.application.request.CreateUserRequest;
import com.ecommerceservice.application.request.UpdateUserRequest;
import com.ecommerceservice.application.response.BasicResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.ecommerceservice.application.common.CommonText.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public BasicResponse create(CreateUserRequest request) {
        log.info(">>> UserService - create - start - request: {}", request);
        if (userRepository.existsByEmail(request.getEmail())) {
            log.error("<<< UserService - create - email already exists");
            throw new InvalidRequestException(HttpStatus.CONFLICT, EMAIL_ALREADY_EXISTS);
        }
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            log.error("<<< UserService - create - phone number already exists");
            throw new InvalidRequestException(HttpStatus.CONFLICT, PHONE_NUMBER_ALREADY_EXISTS);
        }
        User user = request.toEntity();
        user.add(Role.BUYER);
        user.add(Role.SELLER);
        user.add(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        log.info("<<< UserService - create - end - user: {}", user);
        return new BasicResponse(SUCCESS, USER_CREATED);
    }

    public BasicResponse update(UpdateUserRequest request, Authentication authentication) {
        log.info(">>> UserService - update - start - request: {}", request);
        User user = (User) authentication.getPrincipal();
        userRepository.save(request.toEntity(user));
        log.info("<<< UserService - update - end - user: {}", user);
        return new BasicResponse(SUCCESS, USER_UPDATED);
    }

}
