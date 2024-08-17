package com.microservice.cards.constant;

public class CardsConstants {
    private CardsConstants(){}; /* This restricts instantiation */

    public static final String  CREDIT_CARD = "Credit Card";
    public static final int  NEW_CARD_LIMIT = 1_00_000;
    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Account created successfully";
    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request Processed successfully";
    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "An error occurred. Please try again or contact the dev team";
}
