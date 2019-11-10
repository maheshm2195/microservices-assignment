package com.nagarro.microservices.assignment.customermanagement.dto;

import java.util.List;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "customerInformation", schemaVersion = "1.0")
public class CustomerInformation {
	
	@Id
	private int id;
	private String customerName;
	private String address;
	List<String> listOfAccounts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getListOfAccounts() {
		return listOfAccounts;
	}

	public void setListOfAccounts(List<String> listOfAccounts) {
		this.listOfAccounts = listOfAccounts;
	}
}
