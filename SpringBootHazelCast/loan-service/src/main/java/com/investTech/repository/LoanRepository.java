package com.investTech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.investTech.model.Loan;

@Repository
public interface LoanRepository extends MongoRepository<Loan, String> {

}
