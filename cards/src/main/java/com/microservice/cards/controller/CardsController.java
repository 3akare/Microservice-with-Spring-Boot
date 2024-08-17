package com.microservice.cards.controller;

import com.microservice.cards.constant.CardsConstants;
import com.microservice.cards.dto.CardsDto;
import com.microservice.cards.dto.ResponseDto;
import com.microservice.cards.service.ICardsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
@Validated
public class CardsController {
    private static final Logger log = LoggerFactory.getLogger(CardsController.class);
    private final ICardsService iCardService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(
            @RequestParam("mobile_number")
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber
    ){
        log.info("{}", mobileNumber);
        /* create card */
        iCardService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201)
                );
    }

    @GetMapping
    public ResponseEntity<CardsDto> fetchCardDetails(
            @RequestParam("mobile_number")
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber
    ){
        /* get card details */
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iCardService.fetchCard(mobileNumber));
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(
            @Valid @RequestBody CardsDto cardsDto
    ){
        /* update card details */
        boolean isUpdated = iCardService.updateCard(cardsDto);
        if(isUpdated)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(
                            new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200)
                    );
        return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR))
                .body(
                        new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200)
                );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCard(
            @RequestParam("mobile_number")
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber
    ){
        /* delete card */
        boolean isUpdated = iCardService.deleteCard(mobileNumber);
        if(isUpdated)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(
                            new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200)
                    );
        return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR))
                .body(
                        new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200)
                );
    }
}
