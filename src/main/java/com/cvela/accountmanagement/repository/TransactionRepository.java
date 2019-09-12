package com.cvela.accountmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cvela.accountmanagement.account.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
