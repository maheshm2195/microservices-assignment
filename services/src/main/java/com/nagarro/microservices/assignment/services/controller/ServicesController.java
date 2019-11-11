package com.nagarro.microservices.assignment.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.microservices.assignment.services.dto.AccountInformation;
import com.nagarro.microservices.assignment.services.proxy.AccountInformationProxy;

@RestController
public class ServicesController {
	
	@Autowired
	AccountInformationProxy accountInformationProxy;
	
	@PutMapping(path = "/account/order/chequebook/{accountId}")
	public String orderChequeBook(@PathVariable int accountId) {
		String result = "Success";
		AccountInformation accountToUpdate = accountInformationProxy.getAccountInformation(accountId);

		if (accountToUpdate != null) {
			if(accountToUpdate.isChequeBookOrdered()) {
				result = "Cheque book is already ordered";
			} else {
				accountToUpdate.setChequeBookOrdered(true);
				accountInformationProxy.updateAccountInformation(accountToUpdate, accountId);
			}
		} else {
			result = "Account with this ID does not exist";
		}

		return result;
	}
	
	@PutMapping(path = "/account/block/chequebook/{accountId}")
	public String blockChequeBook(@PathVariable int accountId) {
		String result = "Success";
		AccountInformation accountToUpdate = accountInformationProxy.getAccountInformation(accountId);

		if (accountToUpdate != null) {
			if(!accountToUpdate.isChequeBookActive()) {
				result = "Cheque book is already blocked";
			} else {
				accountToUpdate.setChequeBookActive(false);
				accountInformationProxy.updateAccountInformation(accountToUpdate, accountId);
			}
		} else {
			result = "Account with this ID does not exist";
		}

		return result;
	}
	
}
