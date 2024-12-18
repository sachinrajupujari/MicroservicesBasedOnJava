package com.investTech.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.investTech.model.Account;
import com.investTech.service.IAccountService;

@RestController
@RequestMapping(value = "/accountapi/v1")
public class AccountsController {

	Logger logger = LoggerFactory.getLogger(AccountsController.class);
	
	@Autowired
	private IAccountService accountService;

	@GetMapping(value = "/account/{accountId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Account getAccount(@PathVariable String accountId){
		logger.info("getAccount controller method called");
		long startTime = System.currentTimeMillis();
		Account account = accountService.getAccountById(accountId);
		long end = System.currentTimeMillis();
		logger.info("Execution time : " + (end - startTime) +" milisec.");
		return account;
	}

	
	@GetMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getAllAccounts(){
		logger.info("getAllAccounts controller method called");
		long start = System.currentTimeMillis();
		List<Account> accounts = accountService.getAllAccounts();
		long end = System.currentTimeMillis();
		logger.info("Execution time : " + (end - start)+" milisec.");
		return accounts;
	}

	@PostMapping("/insertAccount")
	public Account insertAccount(@RequestBody Account account){
		if(account != null){
			accountService.insertAccount(account);
		}
		return account;
	}

	@PostMapping("/insertAccounts")
	public List<Account> insertAccounts(@RequestBody List<Account> accounts){
		if(accounts != null){
			accountService.insertAccounts(accounts);
		}
		return accounts;
	}
	
	@PutMapping("/updateItem")
	public Account updateItem(@RequestBody Account account,@RequestParam(name = "lock_type",required = false) String lockType){
		logger.info("Lock Type - " + lockType);
		if(account != null){
			if(lockType.equalsIgnoreCase("fenced"))
			{
				//thread based concurrency(Fence Locks)
				accountService.updateAccountWithFenceLock(account);
			}			
			else
			{
				//key based Locking (Imap locks)
				accountService.updateAccount(account);	
			}
				
		}
		return account;
	}

	@DeleteMapping("/delete/{accountId}")
	public ResponseEntity<Void> deleteItem(@PathVariable String accountId){
		accountService.deleteAccount(accountId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/accountsByPredicate", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getAllAccountsByPredicate(@RequestParam(name = "state_id",required = false) String state ,
			@RequestParam(name = "investment_type",required = false,defaultValue = "") String investmentType,
			@RequestParam(name = "loan_amount",required = false,defaultValue = "") String loanAmount,			
			@RequestParam(name = "year_reported",required = false,defaultValue = "") String yearReported){
	    logger.info("passed params are "+state+" "+investmentType+" "+loanAmount+" "+yearReported);
		List<Account> accounts =accountService.getPredicate(state,investmentType,loanAmount,yearReported);
		return accounts;
	}
	
	
}
