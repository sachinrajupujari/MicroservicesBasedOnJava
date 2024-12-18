package com.investTech.service;

import java.util.List;
import java.util.concurrent.locks.Lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import com.investTech.model.Loan;
import com.investTech.repository.LoanRepository;

@Service
public class LoanService implements ILoanService {

	private static final Logger logger = LoggerFactory.getLogger(LoanService.class);

	static final String LOAN_LOCK_KEY = "loan_process_lock_key";	
	
	@Autowired
	private HazelcastInstance hazelcastInstance;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Override
	@Cacheable(value = "loandata")
	public List<Loan> getAllLoanDetails() {
		logger.info("getAllLoanDetails service method called");
		IMap<String, Loan> imap = hazelcastInstance.getMap("loandata");
		List<Loan> loanData = loanRepository.findAll().subList(0, 2000);
		logger.info("LoanData before count " + loanData.size());
		loanData.parallelStream().filter(loan -> loan.getId() != null).forEach(loan -> {
			imap.put(loan.getId(), loan);
		});
		
		return (List<Loan>) imap.values();
	}

	//, key ="#p0", condition="#p0!=null"
	@Override
	//@Cacheable(value = "loandata")
	public List<Loan> getLoanDetailsByState(String stateCode){
		logger.info("getLoanDetailsByState service method called");
		IMap<String, Loan> imap = hazelcastInstance.getMap("loandata");
		
		EntryObject e = new PredicateBuilder().getEntryObject();
		//Predicate predicate1 = e.is( "active" );
		Predicate predicate2 = e.get( "propertyState").equal(stateCode);
		//Predicate predicate = Predicates.and(predicate1, predicate2);
		logger.info("Predicate :: " + predicate2.toString());
		return (List<Loan>) imap.values(predicate2);
	}
	
	
	@Override
	//@Cacheable(value = "loandata")
	public List<Loan> getLoanDetailsWithPredicate(){
		logger.info("getLoanDetailsWithPredicate service method called");
		IMap<String, Loan> map = hazelcastInstance.getMap("loandata");
		logger.info("Map size :: " + map.size());
		
		EntryObject e = new PredicateBuilder().getEntryObject();
		//Predicate predicate = e.is( "active" );
		Predicate predicate = e.get( "creditScore" ).lessThan( "700" );
		return (List<Loan>) map.values(predicate);
		
	}


	@Override
	@CachePut(value = "loandata", key = "#loan.id")
	public Loan updateLoanDetails(Loan loan) {
		logger.info("updateLoanDetails service method called");
		IMap<String, Loan> map = hazelcastInstance.getMap("loandata");

		Lock lock = hazelcastInstance.getCPSubsystem().getLock(LOAN_LOCK_KEY);
		Loan newLoan = null;
		try {
			lock.lock();
			newLoan = loanRepository.save(loan);
			map.put(loan.getId(), newLoan);
			logger.info("new credit score :: " + newLoan.getCreditScore());
		} finally {
			lock.unlock();
		}
		return newLoan;
	}

	
	@Override
	@CacheEvict(value = "loandata", allEntries = true, beforeInvocation = false)
	public String deleteCache() {
		IMap<String, Loan> map = hazelcastInstance.getMap("loandata");
		map.evictAll();
		logger.info("Map size :: " + map.size());
		return map.size() + " records in cache";
		//return "cache cleared";
	}

	@Override
	public Loan insertLoanDetails(Loan loan) {
		logger.info("insertLoanDetails service method called");
		IMap<String, Loan> map = hazelcastInstance.getMap("loandata");
		Loan newLoan = loanRepository.save(loan);
		map.put(loan.getId(), newLoan);
		logger.info("new credit score :: " + newLoan.getId());
		return newLoan;
	}

	@Override
	public void deleteLoanDetails(String loanId) {
		logger.info("deleteLoanDetails service method called");
		IMap<String, Loan> map = hazelcastInstance.getMap("loandata");
		//logger.info("old credit score from cache :: " + map.get(loanId).getCreditScore());
		loanRepository.deleteById(loanId);
		map.evict(loanId);
		map.delete(loanId);
		logger.info("Loan record deleted with id :: " + loanId);
	}
}
