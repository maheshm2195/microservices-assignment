package com.nagarro.microservices.assignment.fundmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.microservices.assignment.fundmanagement.dto.AccountInformation;
import com.nagarro.microservices.assignment.fundmanagement.dto.FundTransferDTO;
import com.nagarro.microservices.assignment.fundmanagement.dto.Transaction;
import com.nagarro.microservices.assignment.fundmanagement.dto.Transaction.TransactionType;
import com.nagarro.microservices.assignment.fundmanagement.proxy.AccountInformationProxy;

@RestController
public class FundManagementController {

	@Autowired
	AccountInformationProxy accountInformationProxy;

	@PutMapping(path = "/account/transaction/{accountId}", consumes = "application/json")
	public String creditDebitAmountInAccount(@RequestBody Transaction transaction,
			@PathVariable int accountId) {
		String result = "Success";
		AccountInformation accountToUpdate = accountInformationProxy.getAccountInformation(accountId);

		if (accountToUpdate != null) {
			accountToUpdate.getPastTransactions().add(transaction);

			if (transaction.getTransactionType().equals(Transaction.TransactionType.CREDIT)) {
				accountToUpdate
						.setBalance(accountToUpdate.getBalance() + transaction.getAmount());
			} else {
				if (accountToUpdate.getBalance() < transaction.getAmount()) {
					result = "Customer's balance is lower than the debit amount.";
				} else {
					accountToUpdate
							.setBalance(accountToUpdate.getBalance() - transaction.getAmount());
				}
			}

			accountInformationProxy.updateAccountInformation(accountToUpdate, accountId);
		} else {
			result = "Account with this ID does not exist";
		}

		return result;
	}

	@PutMapping(path = "/account/transfer", consumes = "application/json")
	public String transferAmountInBetweenAccounts(@RequestBody FundTransferDTO fundTransferDTO) {
		String result = "Success";

		AccountInformation receiverAccount = accountInformationProxy
				.getAccountInformation(fundTransferDTO.getReceiverAccountId());
		AccountInformation senderAccount = accountInformationProxy
				.getAccountInformation(fundTransferDTO.getSenderAccountId());

		if (senderAccount != null && receiverAccount != null) {
			if (senderAccount.getBalance() < fundTransferDTO.getAmount()) {
				result = "Sender's balance is lower than the transfer amount.";
			} else {
				receiverAccount.setBalance(receiverAccount.getBalance() + fundTransferDTO.getAmount());
				senderAccount.setBalance(senderAccount.getBalance() - fundTransferDTO.getAmount());
				receiverAccount.getPastTransactions()
						.add(new Transaction(fundTransferDTO.getAmount(), TransactionType.CREDIT));
				senderAccount.getPastTransactions()
						.add(new Transaction(fundTransferDTO.getAmount(), TransactionType.DEBIT));

				accountInformationProxy.updateAccountInformation(receiverAccount, receiverAccount.getId());
				accountInformationProxy.updateAccountInformation(senderAccount, senderAccount.getId());
			}
		} else {
			result = "Either sender account or receiver account does not exist";
		}

		return result;
	}
}