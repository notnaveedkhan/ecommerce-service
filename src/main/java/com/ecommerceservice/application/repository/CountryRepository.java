package com.ecommerceservice.application.repository;

import com.ecommerceservice.application.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {
    boolean existsByCode(String code);
}