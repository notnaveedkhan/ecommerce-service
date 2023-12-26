package com.ecommerceservice.application.service;

import com.ecommerceservice.application.entity.Item;
import com.ecommerceservice.application.entity.User;
import com.ecommerceservice.application.exception.InvalidRequestException;
import com.ecommerceservice.application.repository.ItemRepository;
import com.ecommerceservice.application.request.CreateItemRequest;
import com.ecommerceservice.application.request.UpdateItemRequest;
import com.ecommerceservice.application.response.BasicResponse;
import com.ecommerceservice.application.response.ItemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.ecommerceservice.application.common.CommonText.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public BasicResponse create(CreateItemRequest request, User user) {
        log.info(">>> ItemService.create - request: {}", request);
        Item item = request.toEntity();
        item.setSeller(user);
        itemRepository.save(item);
        log.info("<<< ItemService.create - item: {}", item);
        return new BasicResponse(SUCCESS, ITEM_CREATED);
    }

    public BasicResponse update(UpdateItemRequest request, User user) {
        log.info(">>> ItemService.update - request: {}", request);
        Item item = itemRepository.findByIdAndSeller(UUID.fromString(request.getId()), user).orElseThrow(() -> {
            log.info("<<< ItemService.update - item not found");
            return new InvalidRequestException(HttpStatus.NOT_FOUND, ITEM_NOT_FOUND);
        });
        item = request.toEntity(item);
        itemRepository.save(item);
        log.info("<<< ItemService.update - item: {}", item);
        return new BasicResponse(SUCCESS, ITEM_UPDATED);
    }

    public List<ItemResponse> list(User user) {
        log.info(">>> ItemService.list - user: {}", user);
        List<Item> items = itemRepository.findBySeller(user);
        log.info("<<< ItemService.list - items: {}", items.size());
        return items.stream()
                .map(ItemResponse::new)
                .toList();
    }

    public BasicResponse delete(UUID uuid, User user) {
        log.info(">>> ItemService.delete - uuid: {}", uuid);
        itemRepository.deleteByIdAndSeller(uuid, user);
        log.info("<<< ItemService.delete - uuid: {}", uuid);
        return new BasicResponse(SUCCESS, ITEM_DELETED);
    }
}
