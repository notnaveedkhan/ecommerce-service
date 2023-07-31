package com.ecommerceservice.application.repository;

import com.ecommerceservice.application.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}