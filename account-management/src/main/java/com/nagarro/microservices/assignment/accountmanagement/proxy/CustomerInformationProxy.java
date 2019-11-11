package com.nagarro.microservices.assignment.accountmanagement.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nagarro.microservices.assignment.accountmanagement.dto.CustomerInformation;

@Component
@FeignClient(name = "zuul-api-gateway")
public interface CustomerInformationProxy {
	
	@GetMapping(path = "/customer-management/customer/{customerId}")
	public CustomerInformation getCustomerInformation(@PathVariable int customerId);

	@PutMapping(path = "/customer-management/customer/{customerId}", consumes = "application/json", produces = "application/json")
	public CustomerInformation updateCustomerInformation(@RequestBody CustomerInformation newCustomerInformation,
			@PathVariable int customerId);

}