package com.nagarro.microservices.assignment.fundmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.nagarro.microservices.assignment.fundmanagement")
@SpringBootApplication
@EnableDiscoveryClient
public class FundManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundManagementApplication.class, args);
	}

}
