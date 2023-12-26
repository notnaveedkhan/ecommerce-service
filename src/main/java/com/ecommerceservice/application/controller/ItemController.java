package com.ecommerceservice.application.controller;

import com.ecommerceservice.application.entity.User;
import com.ecommerceservice.application.request.CreateItemRequest;
import com.ecommerceservice.application.request.UpdateItemRequest;
import com.ecommerceservice.application.response.BasicResponse;
import com.ecommerceservice.application.response.ItemResponse;
import com.ecommerceservice.application.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ItemController extends BaseController {

    private final ItemService itemService;

    @PostMapping(CREATE_ITEM)
    public BasicResponse create(@Valid @RequestBody CreateItemRequest request, Authentication authentication) {
        return itemService.create(request, (User) authentication.getPrincipal());
    }

    @PutMapping(UPDATE_ITEM)
    public BasicResponse update(@Valid @RequestBody UpdateItemRequest request, Authentication authentication) {
        return itemService.update(request, (User) authentication.getPrincipal());
    }

    @GetMapping(ITEM_LIST)
    public List<ItemResponse> list(Authentication authentication) {
        return itemService.list((User) authentication.getPrincipal());
    }

    @DeleteMapping(DELETE_ITEM)
    public BasicResponse delete(@PathVariable String uuid, Authentication authentication) {
        return itemService.delete(UUID.fromString(uuid), (User) authentication.getPrincipal());
    }

}
