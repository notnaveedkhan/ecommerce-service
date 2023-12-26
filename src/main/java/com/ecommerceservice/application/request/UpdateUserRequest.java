package com.ecommerceservice.application.request;

import com.ecommerceservice.application.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UpdateUserRequest implements Serializable {

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate dateOfBirth;

    public User toEntity(User user) {
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setGender(this.getGender());
        user.setDateOfBirth(this.getDateOfBirth());
        return user;
    }

}
