package com.microservice.cards.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "contact")
public class CardsContactDto {
    private String author;
    private String profile;
    private String service;
    private String url;
}
