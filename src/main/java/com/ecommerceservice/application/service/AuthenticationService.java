package com.ecommerceservice.application.service;

import com.ecommerceservice.application.entity.User;
import com.ecommerceservice.application.exception.InvalidRequestException;
import com.ecommerceservice.application.repository.UserRepository;
import com.ecommerceservice.application.request.AuthenticationRequest;
import com.ecommerceservice.application.response.AuthenticationResponse;
import com.ecommerceservice.application.response.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static com.ecommerceservice.application.common.CommonText.INVALID_CREDENTIALS;
import static com.ecommerceservice.application.common.CommonText.SOMETHING_WENT_WRONG;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder encoder;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info(">>> AuthenticationService - authenticate request : {}", request);
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            log.error("<<< AuthenticationService - authenticate - user not found");
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, INVALID_CREDENTIALS);
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.error("<<< AuthenticationService - authenticate - password not match");
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, INVALID_CREDENTIALS);
        }
        AuthenticationResponse response = new AuthenticationResponse();
        response.setStatus("SUCCESS");
        response.setMessage("Authentication Success");
        Instant now = Instant.now();
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("ecommerce-service")
                    .subject(user.getId().toString())
                    .issuedAt(now)
                    .expiresAt(now.plus(2, ChronoUnit.HOURS))
                    .claim("user", mapper.writeValueAsString(user))
                    .build();
            response.setToken(encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
            response.setCreatedAt(now.toString());
            response.setExpiresAt(now.plus(2, ChronoUnit.HOURS).toString());
            response.setUser(new UserResponse(user));
            log.info("<<< AuthenticationService - authenticate response : {}", response);
            return response;
        } catch (JsonProcessingException e) {
            log.error("<<< AuthenticationService - authenticate response : ", e);
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, SOMETHING_WENT_WRONG);
        }
    }
}
