package com.ecommerceservice.application.controller;

import com.ecommerceservice.application.entity.User;
import com.ecommerceservice.application.request.CreateCardRequest;
import com.ecommerceservice.application.request.UpdateCardRequest;
import com.ecommerceservice.application.response.BasicResponse;
import com.ecommerceservice.application.response.CardResponse;
import com.ecommerceservice.application.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CardController extends BaseController {

    private final CardService cardService;

    @PostMapping(CREATE_CARD)
    public BasicResponse create(@Valid @RequestBody CreateCardRequest request, Authentication authentication) {
        return cardService.create(request, (User) authentication.getPrincipal());
    }

    @PutMapping(UPDATE_CARD)
    public BasicResponse update(@Valid @RequestBody UpdateCardRequest request) {
        return cardService.update(request);
    }

    @GetMapping(CARD_LIST)
    public List<CardResponse> list(Authentication authentication) {
        return cardService.list((User) authentication.getPrincipal());
    }

    @DeleteMapping(DELETE_CARD)
    public BasicResponse delete(@PathVariable String uuid) {
        return cardService.delete(UUID.fromString(uuid));
    }
}
