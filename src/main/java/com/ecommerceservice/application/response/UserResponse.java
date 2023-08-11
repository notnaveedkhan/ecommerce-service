package com.ecommerceservice.application.response;

import com.ecommerceservice.application.entity.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String phoneNumber;
    private List<String> roles;

    public UserResponse(User user) {
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setEmail(user.getEmail());
        setGender(user.getGender());
        setPhoneNumber(user.getPhoneNumber());
        setRoles(user.getRoles().stream().map(Enum::name).toList());
    }
}
