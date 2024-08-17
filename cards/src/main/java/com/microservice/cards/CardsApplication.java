package com.microservice.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl") /* All this to handle @ModifiedBy */
@OpenAPIDefinition(info = @Info(
		title = "Cards microservice REST API Documentation",
		description = "Bank Cards microservice REST API Documentation",
		version = "v1",
		contact = @Contact(
				name = "David Bakare",
				email = "bakaredavid007@gmail.com"
		),
		license = @License(
				name = "Apache 2.0"
		)),
		externalDocs =  @ExternalDocumentation(
				description = "Bank Cards microservice REST API Documentation",
				url = "https://github.com/3akare/Microservices-with-Spring-Boot.git"
		)
)
public class CardsApplication {
	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}
}
