package com.cvela.accountmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cvela.accountmanagement.account.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
