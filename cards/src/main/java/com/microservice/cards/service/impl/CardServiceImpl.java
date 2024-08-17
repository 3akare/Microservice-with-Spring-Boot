package com.microservice.cards.service.impl;

import com.microservice.cards.constant.CardsConstants;
import com.microservice.cards.dto.CardsDto;
import com.microservice.cards.entity.Cards;
import com.microservice.cards.exception.CardAlreadyExistsException;
import com.microservice.cards.exception.ResourceNotFound;
import com.microservice.cards.mapper.CardMapper;
import com.microservice.cards.repository.CardsRepository;
import com.microservice.cards.service.ICardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements ICardsService {
    private final CardsRepository cardsRepository;

    /**
     *
     * @param mobileNumber  - String object
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> cards = cardsRepository.findByMobileNumber(mobileNumber);
        if(cards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));

    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFound("Cards", "mobileNumber", mobileNumber)
        );

        return CardMapper.mapToCardDto(cards, new CardsDto());
    }

    /**
     * @param cardsDto - CardDto object
     * @return boolean
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardsRepository.findByMobileNumber(cardsDto.getMobileNumber()).orElseThrow(
                () -> new ResourceNotFound("Cards", "mobileNumber", cardsDto.getMobileNumber())
        );
        cardsRepository.save(CardMapper.mapToCard(cards, cardsDto));
        return true;
    }

    /**
     * @param mobileNumber - String object
     * @return boolean
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFound("Cards", "mobileNumber", mobileNumber)
        );
        cardsRepository.delete(cards);
        return true;
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new card details
     */
    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }
}
