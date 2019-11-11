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
	List<Integer> listOfAccounts;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the listOfAccounts
	 */
	public List<Integer> getListOfAccounts() {
		return listOfAccounts;
	}
	/**
	 * @param listOfAccounts the listOfAccounts to set
	 */
	public void setListOfAccounts(List<Integer> listOfAccounts) {
		this.listOfAccounts = listOfAccounts;
	}
}