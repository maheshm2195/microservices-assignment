package com.nagarro.microservices.assignment.fundmanagement.dto;

public class FundTransferDTO {

	private double amount;
	private int senderAccountId;
	private int receiverAccountId;
	
	/**
	 * @return the senderAccountId
	 */
	public int getSenderAccountId() {
		return senderAccountId;
	}
	/**
	 * @param senderAccountId the senderAccountId to set
	 */
	public void setSenderAccountId(int senderAccountId) {
		this.senderAccountId = senderAccountId;
	}
	/**
	 * @return the receiverAccountId
	 */
	public int getReceiverAccountId() {
		return receiverAccountId;
	}
	/**
	 * @param receiverAccountId the receiverAccountId to set
	 */
	public void setReceiverAccountId(int receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}
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
}
