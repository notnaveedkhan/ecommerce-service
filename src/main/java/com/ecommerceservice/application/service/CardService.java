package com.ecommerceservice.application.service;

import com.ecommerceservice.application.entity.Card;
import com.ecommerceservice.application.entity.User;
import com.ecommerceservice.application.exception.InvalidRequestException;
import com.ecommerceservice.application.repository.CardRepository;
import com.ecommerceservice.application.request.CreateCardRequest;
import com.ecommerceservice.application.request.UpdateCardRequest;
import com.ecommerceservice.application.response.BasicResponse;
import com.ecommerceservice.application.response.CardResponse;
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
public class CardService {

    private final CardRepository cardRepository;

    public BasicResponse create(CreateCardRequest request, User user) {
        log.info(">>> CardService.create - request: {}", request);
        Card card = request.toEntity();
        card.setUser(user);
        cardRepository.save(card);
        log.info("<<< CardService.create - card: {}", card);
        return new BasicResponse(SUCCESS, CARD_CREATED);
    }

    public BasicResponse update(UpdateCardRequest request) {
        log.info(">>> CardService.update - request: {}", request);
        Card card = cardRepository.findById(UUID.fromString(request.getId())).orElseThrow(() -> {
            log.info("<<< CardService.update - card not found");
            return new InvalidRequestException(HttpStatus.NOT_FOUND, CARD_NOT_FOUND);
        });
        card = request.toEntity(card);
        cardRepository.save(card);
        log.info("<<< CardService.update - card: {}", card);
        return new BasicResponse(SUCCESS, CARD_UPDATED);
    }

    public List<CardResponse> list(User user) {
        log.info(">>> CardService.list - user: {}", user);
        List<Card> cards = cardRepository.findByUser(user);
        log.info("<<< CardService.list - cards: {}", cards.size());
        return cards.stream()
                .map(CardResponse::new)
                .toList();
    }

    public BasicResponse delete(UUID uuid) {
        log.info(">>> CardService.delete");
        cardRepository.deleteById(uuid);
        log.info("<<< CardService.delete");
        return new BasicResponse(SUCCESS, CARD_DELETED);
    }
}
