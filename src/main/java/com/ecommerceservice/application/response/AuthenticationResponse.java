package com.ecommerceservice.application.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse extends BasicResponse {

    private String token;
    private String createdAt;
    private String expiresAt;
    private UserResponse user;

}
