package com.nagarro.microservices.assignment.accountmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.microservices.assignment.accountmanagement.dto.AccountInformation;
import com.nagarro.microservices.assignment.accountmanagement.dto.NewBranch;
import com.nagarro.microservices.assignment.accountmanagement.dto.Transaction;

import io.jsondb.InvalidJsonDbApiUsageException;
import io.jsondb.JsonDBTemplate;

@RestController
public class AccountManagementController {
	String dbFilesLocation = "D:\\OneDrive - Nagarro\\D_Drive\\NAGP\\Session 6-microservices\\Assignment\\projects";
	String baseScanPackage = "com.nagarro.microservices.assignment.accountmanagement.dto";
	JsonDBTemplate jsonDBTemplate = new JsonDBTemplate(dbFilesLocation, baseScanPackage);

	public AccountManagementController() {
		try {
			jsonDBTemplate.createCollection(AccountInformation.class);
		} catch (InvalidJsonDbApiUsageException exc) {
			System.out.println("Collection exists");
		}
	}
	
	@GetMapping(path="/account")
	public List<AccountInformation> getAllAccountInformation() {
		return jsonDBTemplate.findAll(AccountInformation.class);
	}

	@GetMapping(path="/account/{accountId}")
	public AccountInformation getAccountInformation(@PathVariable int accountId) {
		return jsonDBTemplate.findById(accountId, AccountInformation.class);
	}
	
	@GetMapping(path="/account/transactions/{accountId}")
	public List<Transaction> getAccountTransactions(@PathVariable int accountId) {
		AccountInformation account = jsonDBTemplate.findById(accountId, AccountInformation.class);
		return account == null ? new ArrayList<>() : account.getPastTransactions();
	}

	@PutMapping(path="/account/{accountId}", consumes = "application/json", produces = "application/json")
	public AccountInformation updateAccountInformation(@RequestBody AccountInformation newAccountInformation,
			@PathVariable int accountId) {
		AccountInformation accountToUpdate = jsonDBTemplate.findById(accountId, AccountInformation.class);

		if (accountToUpdate != null) {
			accountToUpdate.setAccountActive(newAccountInformation.isAccountActive());
			accountToUpdate.setBalance(newAccountInformation.getBalance());
			accountToUpdate.setBranch(newAccountInformation.getBranch());
			accountToUpdate.setChequeBookActive(newAccountInformation.isChequeBookActive());
			accountToUpdate.setCustomerId(newAccountInformation.getCustomerId());
			accountToUpdate.setPastTransactions(newAccountInformation.getPastTransactions());

			jsonDBTemplate.upsert(accountToUpdate);
		}

		return accountToUpdate;
	}

	@PutMapping(path="/account/transfer/{accountId}", consumes = "application/json", produces = "application/json")
	public AccountInformation changeAccountBranch(@RequestBody NewBranch newBranchDto, @PathVariable int accountId) {
		AccountInformation accountToUpdate = jsonDBTemplate.findById(accountId, AccountInformation.class);

		if (accountToUpdate != null) {
			accountToUpdate.setBranch(newBranchDto.getNewBranch());

			jsonDBTemplate.upsert(accountToUpdate);
		}

		return accountToUpdate;
	}

	@PutMapping(path="/account/activate/{accountId}", consumes = "application/json", produces = "application/json")
	public AccountInformation activateAccount(@PathVariable int accountId) {
		AccountInformation accountToUpdate = jsonDBTemplate.findById(accountId, AccountInformation.class);

		if (accountToUpdate != null) {
			accountToUpdate.setAccountActive(true);
			jsonDBTemplate.upsert(accountToUpdate);
		}

		return accountToUpdate;
	}

	@PutMapping(path="/account/deactivate/{accountId}", consumes = "application/json", produces = "application/json")
	public AccountInformation deactivateAccount(@PathVariable int accountId) {
		AccountInformation accountToUpdate = jsonDBTemplate.findById(accountId, AccountInformation.class);

		if (accountToUpdate != null) {
			accountToUpdate.setAccountActive(false);
			jsonDBTemplate.upsert(accountToUpdate);
		}

		return accountToUpdate;
	}

	@PostMapping(path = "/account", consumes = "application/json", produces = "application/json")
	public AccountInformation createNewAccount(@RequestBody AccountInformation accountInformation) {
		accountInformation.setId(getNextId());
		jsonDBTemplate.insert(accountInformation);
		
		return accountInformation;
	}

	private int getNextId() {
		List<AccountInformation> allAccounts = jsonDBTemplate.findAll(AccountInformation.class);
		return allAccounts.size();
	}
}