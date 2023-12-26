package com.ecommerceservice.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate dateOfBirth;

    @Column(unique = true)
    private String email;

    private boolean emailVerified;

    private boolean accountLocked;

    @ToString.Exclude
    private String password;

    private String phoneNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof User user) {
            return getId() != null && Objects.equals(getId(), user.getId());
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void add(Role role) {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(role);
    }
}
