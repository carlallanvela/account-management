package com.cvela.accountmanagement;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cvela.accountmanagement.account.Account;
import com.cvela.accountmanagement.repository.AccountRepository;
import com.cvela.accountmanagement.repository.TransactionRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Test
	public void testFindAll() {
		List<Account> allAccounts = accountRepository.findAll();
		// 3 initial accounts
		assertThat(allAccounts.size() == 3);
	}
	
	@Test
	public void testFindOne() {
		Optional<Account> account = accountRepository.findById(10001);
		assertThat(account.get().getName() == "Account 1");
	}
	
	@Test
	public void testSave() {
		Account newAccount = accountRepository.save(new Account(1, "New Account", new Date()));
		Optional<Account> searchedAccount = accountRepository.findById(1);
		assertThat(newAccount.getId() == searchedAccount.get().getId());
	}
}
