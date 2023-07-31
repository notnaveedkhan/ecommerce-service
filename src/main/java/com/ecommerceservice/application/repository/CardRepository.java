package com.ecommerceservice.application.repository;

import com.ecommerceservice.application.entity.Card;
import com.ecommerceservice.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
    List<Card> findByUser(User user);
}