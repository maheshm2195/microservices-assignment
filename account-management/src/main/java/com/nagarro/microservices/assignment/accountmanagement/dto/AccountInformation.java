package com.nagarro.microservices.assignment.accountmanagement.dto;

import java.util.List;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "accountInformation", schemaVersion = "1.0")
public class AccountInformation {
	
	@Id
	private int id;
	private int customerId;
	private String branch;
	private boolean isAccountActive;
	private double balance;
	private List<Transaction> pastTransactions;
	private boolean isChequeBookActive;
	
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
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}
	/**
	 * @return the isAccountActive
	 */
	public boolean isAccountActive() {
		return isAccountActive;
	}
	/**
	 * @param isAccountActive the isAccountActive to set
	 */
	public void setAccountActive(boolean isAccountActive) {
		this.isAccountActive = isAccountActive;
	}
	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	/**
	 * @return the pastTransactions
	 */
	public List<Transaction> getPastTransactions() {
		return pastTransactions;
	}
	/**
	 * @param pastTransactions the pastTransactions to set
	 */
	public void setPastTransactions(List<Transaction> pastTransactions) {
		this.pastTransactions = pastTransactions;
	}
	/**
	 * @return the isChequeBookActive
	 */
	public boolean isChequeBookActive() {
		return isChequeBookActive;
	}
	/**
	 * @param isChequeBookActive the isChequeBookActive to set
	 */
	public void setChequeBookActive(boolean isChequeBookActive) {
		this.isChequeBookActive = isChequeBookActive;
	}
}