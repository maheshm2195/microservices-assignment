package com.nagarro.microservices.assignment.customermanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.microservices.assignment.customermanagement.dto.CustomerInformation;

import io.jsondb.InvalidJsonDbApiUsageException;
import io.jsondb.JsonDBTemplate;

@RestController
public class CustomerManagementController {

	String dbFilesLocation = "D:\\OneDrive - Nagarro\\D_Drive\\NAGP\\Session 6-microservices\\Assignment\\projects";
	String baseScanPackage = "com.nagarro.microservices.assignment.customermanagement.dto";
	JsonDBTemplate jsonDBTemplate = new JsonDBTemplate(dbFilesLocation, baseScanPackage);

	public CustomerManagementController() {
		try {
			jsonDBTemplate.createCollection(CustomerInformation.class);
		} catch (InvalidJsonDbApiUsageException exc) {
			System.out.println("Collection exists");
		}
	}

	@GetMapping("/customer/{customerId}")
	public CustomerInformation getCustomerInformation(@PathVariable int customerId) {
		return jsonDBTemplate.findById(customerId, CustomerInformation.class);
	}

	@GetMapping("/customer/accounts/{customerId}")
	public List<String> getCustomerAccounts(@PathVariable int customerId) {
		CustomerInformation customer = jsonDBTemplate.findById(customerId, CustomerInformation.class);
		return customer == null ? new ArrayList<>() : customer.getListOfAccounts();
	}

	@PutMapping("/customer/{customerId}")
	public CustomerInformation updateCustomerInformation(@RequestBody CustomerInformation newCustomerInformation,
			@PathVariable int customerId) {
		CustomerInformation customerToUpdate = jsonDBTemplate.findById(customerId, CustomerInformation.class);

		customerToUpdate.setCustomerName(newCustomerInformation.getCustomerName());
		customerToUpdate.setAddress(newCustomerInformation.getAddress());
		customerToUpdate.setListOfAccounts(newCustomerInformation.getListOfAccounts());

		jsonDBTemplate.upsert(customerToUpdate);

		return newCustomerInformation;
	}

	@PostMapping(path = "/customer", consumes = "application/json", produces = "application/json")
	public void createNewCustomer(@RequestBody CustomerInformation customerInformation) {
		CustomerInformation newCustomer = new CustomerInformation();

		newCustomer.setId(getNextId());
		newCustomer.setCustomerName(customerInformation.getCustomerName());
		newCustomer.setAddress(customerInformation.getAddress());
		newCustomer.setListOfAccounts(customerInformation.getListOfAccounts());

		jsonDBTemplate.insert(newCustomer);
	}

	private int getNextId() {
		List<CustomerInformation> allCustomers = jsonDBTemplate.findAll(CustomerInformation.class);
		return allCustomers.size();
	}
}
