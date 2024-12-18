package com.investTech.services.hazelcastmemberservice.mapstore;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MapLoaderLifecycleSupport;
import com.hazelcast.core.MapStore;
import com.hazelcast.spring.context.SpringAware;
import com.investTech.model.Account;
import com.investTech.services.hazelcastmemberservice.repository.AccountRepository;

@SpringAware
@Component
public class AccountMapStore implements MapStore<String, Account>,MapLoaderLifecycleSupport {
    
	Logger logger = LoggerFactory.getLogger(AccountMapStore.class);
	

	   @Autowired
	   private  AccountRepository accountRepository;
	    
	  public AccountMapStore() {
		  logger.info("Invoking the Account Map Store ");
	  }
	    
	   
		@Override
		public Account load(String key) {
			
			
				Optional<Account> account = accountRepository.findById(key);
		        return account.isPresent() ? account.get() : null;
			
		}

		@Override
		public Map<String, Account> loadAll(Collection<String> keys) {
			// TODO Auto-generated method stub
			Map<String, Account> result = new HashMap<>();
			logger.info("******************************      Loading All Data for Given Keys   ******************************");
	        for (String key : keys) {
	        	
	        	Account account = this.load(key);
	            if (account != null) {
	                result.put(key, account);
	            }
	        }
	        return result;
		}

		@Override
		public Iterable<String> loadAllKeys() {
			 logger.info("******************************      Loading All keys   ******************************");
			// TODO Auto-generated method stub
			Map<String, Account> accounts = new HashMap<String, Account>();
	        List<Account> accountsList = accountRepository.findAll().subList(0, 2000);
	        for (Account account : accountsList) {
	        	accounts.put(account.getId(), account);
	        }
	        return accounts.keySet();
		}

	

		@Override
		public void store(String key, Account account) {
			// TODO Auto-generated method stub
			logger.info("Persisiting the new record to ");
			accountRepository.save(account);
		}

		@Override
		public void storeAll(Map<String, Account> map) {
			// TODO Auto-generated method stub
			accountRepository.saveAll(new ArrayList<>(map.values()));
		}

		@Override
		public void delete(String key) {
			// TODO Auto-generated method stub
			accountRepository.deleteById(key);
		}

		@Override
		public void deleteAll(Collection<String> keys) {
			// TODO Auto-generated method stub
			keys.forEach(accountRepository::deleteById);
		}


		

		
		@Override
		public void init(HazelcastInstance hazelcastInstance, Properties properties, String mapName) {
			// TODO Auto-generated method stub
			 hazelcastInstance.getConfig().getManagedContext().initialize(this);
		}

		@Override
		public void destroy() {
			// TODO Auto-generated method stub
			
		}

		

		

		
}
