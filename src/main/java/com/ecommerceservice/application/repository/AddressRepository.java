package com.ecommerceservice.application.repository;

import com.ecommerceservice.application.entity.Address;
import com.ecommerceservice.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findByUser(User user);
}