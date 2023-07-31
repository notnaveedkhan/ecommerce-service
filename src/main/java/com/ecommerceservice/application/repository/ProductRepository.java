package com.ecommerceservice.application.repository;

import com.ecommerceservice.application.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Item, UUID> {
}