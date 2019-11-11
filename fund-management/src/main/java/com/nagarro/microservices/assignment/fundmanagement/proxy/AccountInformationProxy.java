package com.nagarro.microservices.assignment.fundmanagement.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nagarro.microservices.assignment.fundmanagement.dto.AccountInformation;

@Component
@FeignClient(name = "zuul-api-gateway")
public interface AccountInformationProxy {

	@GetMapping(path = "/account-management/account/{accountId}")
	public AccountInformation getAccountInformation(@PathVariable int accountId);

	@PutMapping(path = "/account-management/account/{accountId}", consumes = "application/json", produces = "application/json")
	public AccountInformation updateAccountInformation(@RequestBody AccountInformation newAccountInformation,
			@PathVariable int accountId);
}