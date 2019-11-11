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
	
	@GetMapping(path = "/customer")
	public List<CustomerInformation> getAllAccountInformation() {
		return jsonDBTemplate.findAll(CustomerInformation.class);
	}

	@GetMapping(path = "/customer/{customerId}")
	public CustomerInformation getCustomerInformation(@PathVariable int customerId) {
		return jsonDBTemplate.findById(customerId, CustomerInformation.class);
	}

	@GetMapping(path="/customer/accounts/{customerId}")
	public List<Integer> getCustomerAccounts(@PathVariable int customerId) {
		CustomerInformation customer = jsonDBTemplate.findById(customerId, CustomerInformation.class);
		return customer == null ? new ArrayList<>() : customer.getListOfAccounts();
	}

	@PutMapping(path="/customer/{customerId}", consumes = "application/json", produces = "application/json")
	public CustomerInformation updateCustomerInformation(@RequestBody CustomerInformation newCustomerInformation,
			@PathVariable int customerId) {
		CustomerInformation customerToUpdate = jsonDBTemplate.findById(customerId, CustomerInformation.class);

		if (customerToUpdate != null) {
			customerToUpdate.setCustomerName(newCustomerInformation.getCustomerName());
			customerToUpdate.setAddress(newCustomerInformation.getAddress());
			customerToUpdate.setListOfAccounts(newCustomerInformation.getListOfAccounts());

			jsonDBTemplate.upsert(customerToUpdate);
		}
		return customerToUpdate;
	}

	@PostMapping(path = "/customer", consumes = "application/json", produces = "application/json")
	public CustomerInformation createNewCustomer(@RequestBody CustomerInformation customerInformation) {
		customerInformation.setId(getNextId());
		jsonDBTemplate.insert(customerInformation);
		return customerInformation;
	}

	private int getNextId() {
		List<CustomerInformation> allCustomers = jsonDBTemplate.findAll(CustomerInformation.class);
		return allCustomers.size();
	}
}
