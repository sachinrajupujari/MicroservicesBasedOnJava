package com.investTech.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.investTech.model.Loan;

@Service
public interface ILoanService {
	
	List<Loan> getAllLoanDetails();

	List<Loan> getLoanDetailsByState(String stateCode);

	List<Loan> getLoanDetailsWithPredicate();

	Loan updateLoanDetails(Loan loan);

	String deleteCache();

	Loan insertLoanDetails(Loan loan);

	void deleteLoanDetails(String loanId);

}
