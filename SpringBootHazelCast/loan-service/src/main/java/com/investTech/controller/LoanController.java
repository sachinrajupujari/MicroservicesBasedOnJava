package com.investTech.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.investTech.model.Loan;
import com.investTech.service.ILoanService;

@RestController
@RequestMapping(value = "/loanservice/v1")
public class LoanController {

	private static final Logger logger = LoggerFactory.getLogger(LoanController.class);
	
	@Autowired
	private ILoanService loanService;
	
	@GetMapping(value = "/loans", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Loan> getAllLoanDetails(){
		logger.info("getAllLoanDetails controller method called");
		long start = System.currentTimeMillis();
		List<Loan> loans = loanService.getAllLoanDetails();
		long end = System.currentTimeMillis();
		logger.info("Execution time : " + (end - start)+" milisec.");
		return loans;
	}
	
	@GetMapping(value = "/loansByState/{stateCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Loan> getLoanDetailsByState(@PathVariable String stateCode){
		logger.info("getLoanDetailsByState controller method called with param :: " + stateCode);
		long start = System.currentTimeMillis();
		List<Loan> loans = loanService.getLoanDetailsByState(stateCode);
		logger.info("LoanData after count " + loans.size());

		long end = System.currentTimeMillis();
		logger.info("Execution time : " + (end - start)+" milisec.");
		return loans;
	}
	
	
	@GetMapping(value = "/loansWithPredicate", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Loan> getLoanDetailsWithPredicate(){
		logger.info("getLoanDetailsWithPredicate controller method called");
		long start = System.currentTimeMillis();
		List<Loan> loans = loanService.getLoanDetailsWithPredicate();
		long end = System.currentTimeMillis();
		logger.info("Execution time : " + (end - start)+" milisec.");
		return loans;
	}
	
	@PutMapping(value = "/updateLoanDetails")
	public Loan updateLoanDetails(@RequestBody Loan loan ) {
		logger.info("updateLoanDetails controller method called");
		long start = System.currentTimeMillis();
		Loan result = loanService.updateLoanDetails(loan);
		long end = System.currentTimeMillis();
		logger.info("Execution time : " + (end - start)+" milisec.");
		return result;
	}
	
	@PostMapping(value = "/insertLoanDetails")
	public Loan insertLoanDetails(@RequestBody Loan loan ) {
		logger.info("insertLoanDetails controller method called");
		long start = System.currentTimeMillis();
		Loan result = loanService.insertLoanDetails(loan);
		long end = System.currentTimeMillis();
		logger.info("Execution time : " + (end - start)+" milisec.");
		return result;
	}
	
	@DeleteMapping(value = "/deleteLoanDetails/{loanId}")
	public void deleteLoanDetails(@PathVariable String loanId ) {
		logger.info("insertLoanDetails controller method called");
		long start = System.currentTimeMillis();
		loanService.deleteLoanDetails(loanId);
		long end = System.currentTimeMillis();
		logger.info("Execution time : " + (end - start)+" milisec.");
	}
	
	@GetMapping(value = "/deleteCache")
	public String deleteCache() {
		logger.info("deleteCache controller method called");
		long start = System.currentTimeMillis();
		String result = loanService.deleteCache();
		long end = System.currentTimeMillis();
		logger.info("Execution time : " + (end - start)+" milisec.");
		return result;
	}
}
