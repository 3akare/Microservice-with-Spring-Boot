package com._akare.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class GatewayserverApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

}
