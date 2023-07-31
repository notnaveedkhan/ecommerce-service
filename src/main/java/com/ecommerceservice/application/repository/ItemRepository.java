package com.ecommerceservice.application.repository;


import com.ecommerceservice.application.entity.Item;
import com.ecommerceservice.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {

    List<Item> findBySeller(User user);

    int deleteByIdAndSeller(UUID id, User user);

    Optional<Item> findByIdAndSeller(UUID uuid, User user);
}
