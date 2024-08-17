package com.microservice.cards.service;

import com.microservice.cards.dto.CardsDto;

public interface ICardsService {
    /**
     * @param mobileNumber  - String object
     */
    void createCard(String mobileNumber);

    /**
     * @param mobileNumber  -String object
     * @return CardDto object
     */
    CardsDto fetchCard(String mobileNumber);

    /**
     * @param cardsDto - CardDto object
     * @return boolean
     */
    boolean updateCard(CardsDto cardsDto);

    /**
     * @param mobileNumber - String object
     * @return boolean
     */
    boolean deleteCard(String mobileNumber);
}
