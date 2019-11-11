package com.nagarro.microservices.assignment.services.dto;

public class Transaction {
	public enum TransactionType {
	    CREDIT,
	    DEBIT
	}
	
	public Transaction(double amount, TransactionType transactionType) {
		super();
		this.amount = amount;
		this.transactionType = transactionType;
	}
	
	public Transaction() {
		super();
	}
	
	private double amount;
	private TransactionType transactionType;
	
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the transactionType
	 */
	public TransactionType getTransactionType() {
		return transactionType;
	}
	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	
}
