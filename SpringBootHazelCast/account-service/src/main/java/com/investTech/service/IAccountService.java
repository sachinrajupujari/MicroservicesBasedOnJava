package com.investTech.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.investTech.model.Account;

@Service
public interface IAccountService {

	void insertAccount(Account account);
	
	void insertAccounts(List<Account> accounts);
	
	void updateAccount(Account account);
	
	List<Account> getAllAccounts();
	
	Account getAccountById(String accountId);
	
	void deleteAccount(String accountId);

	List<Account> getPredicate(String state, String investmentType, String loanAmount, String yearReported);

	String deleteAccounts();

	void updateAccountWithFenceLock(Account account);



	
}
