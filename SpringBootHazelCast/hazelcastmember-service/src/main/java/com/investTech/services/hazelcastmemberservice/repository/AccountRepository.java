package com.investTech.services.hazelcastmemberservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.investTech.model.Account;

@Repository("accountRepository")
public interface AccountRepository extends MongoRepository<Account, String> {

}
