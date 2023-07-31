package com.ecommerceservice.application.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class AuthenticationRequest implements Serializable {

    @NotBlank(message = "Email is required")
    private String email;

    @ToString.Exclude
    @NotBlank(message = "Password is required")
    private String password;

}
