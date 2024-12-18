package com.investTech.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.cp.lock.FencedLock;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import com.hazelcast.query.Predicates;
import com.investTech.config.RandomString;
import com.investTech.model.Account;




/*
 * @CacheConfig(cacheNames = "accounts")
 */
@Service
public class AccountService implements IAccountService {

	Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	/*
	 * @Autowired private AccountRepository accountRepository;
	 */
	
	@Autowired
	HazelcastInstance hazelcastInstance;

	@Override
	public void insertAccount(Account account) {
		//accountRepository.insert(account);
		 String guid=RandomString.getAlphaNumericString();
		 System.out.println(guid);
		 account.setId(guid); 
		 IMap<String , Account> dataStore = 
				 hazelcastInstance.getMap("accounts");
         dataStore.put(account.getId(), account);
		
	}

	@Override
	public void insertAccounts(List<Account> accounts) {
		//accountRepository.insert(accounts);
		IMap<String , Account> dataStore = 
				hazelcastInstance.getMap("accounts");
       dataStore.putAll(dataStore);
	}

	
	@Override
	//@CachePut(value="accounts")
	public void updateAccountWithFenceLock(Account account) {
		//accountRepository.save(account);
		IMap<String , Account> dataStore = 
				hazelcastInstance.getMap("accounts");
		FencedLock fencedLock=hazelcastInstance.getCPSubsystem().getLock("non-reentrant-lock");
		
		//Pessimistic locking 
		try {
			logger.info("request the  Fenced Lock");
			// dataStore.lock(account.getId());
			  fencedLock.lock();
			 logger.info("obtained the Fenced  lock");
			dataStore.put(account.getId(),account,1200,TimeUnit.SECONDS);
		    Thread.sleep(10000);
		}
		catch(Exception e) {
			logger.error("issue while updating the account using the Fenced Lock");
		}
		finally {
			//dataStore.unlock(account.getId());
			 if (fencedLock.isLockedByCurrentThread()) 
				 fencedLock.unlock();
		        
			
		}
		logger.info("data updated suceesfully using Fenced Lock");
	}
	
	
	@Override
	//@CachePut(value="accounts")
	public void updateAccount(Account account) {
		//accountRepository.save(account);
		IMap<String , Account> dataStore = 
				hazelcastInstance.getMap("accounts");
		
		//Pessimistic locking 
		try {
			logger.info("request the Map Lock");
			 dataStore.lock(account.getId());
			 
			 logger.info("obtained the  Map lock");
			dataStore.put(account.getId(),account,1200,TimeUnit.SECONDS);
		    Thread.sleep(10000);
		}
		catch(Exception e) {
			logger.error("issue while updating the account using Map lock");
		}
		finally {
			dataStore.unlock(account.getId());
           }
		logger.info("data updated suceesfully  using Map lock");
	}

	@Override
	
	public List<Account> getAllAccounts() {
		logger.info("getAllAccounts service method called");
		//return accountRepository.findAll();
		 IMap<String , Account> dataStore = 
				 hazelcastInstance.getMap("accounts");
        return new ArrayList<Account>(dataStore.values());
	
	}

	@Override
	//@Cacheable(value="accounts",key="#id", unless="#result == null")
	public Account getAccountById(String accountId) {
		//return accountRepository.findById(accountId).get();
		IMap<String , Account> dataStore = 
                hazelcastInstance.getMap("accounts");
         return dataStore.get(accountId);
	}

	@Override
	public void deleteAccount(String accountId) {
		//accountRepository.deleteById(accountId);
		IMap<String , Account> dataStore = 
                hazelcastInstance.getMap("accounts");
          dataStore.remove(accountId);
	}
	
	@Override
	public String deleteAccounts() {
		//accountRepository.deleteById(accountId);
		IMap<String , Account> dataStore = 
                hazelcastInstance.getMap("accounts");
		int mapsize=dataStore.size();
		logger.info("Map size :: " +mapsize );
          dataStore.evictAll();          
  		return mapsize + " records cleared in cache";
  		
	}
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public	List<Account> getPredicate(String state, String investmentType, String loanAmount, String yearReported)
	{
		IMap<String , Account> dataStore = 
                hazelcastInstance.getMap("accounts");
		
		   EntryObject e = new PredicateBuilder().getEntryObject();
		    Predicate finalPredicate = e.get( "stateId" ).equal(state).and(e.get( "programType" ).equal(investmentType).and(e.get( "yearReported" ).equal(yearReported).and(e.get( "loanInvestAmount" ).greaterEqual(Integer.parseInt(loanAmount)))));
		return (List<Account>)dataStore.values(finalPredicate);
	}
}
