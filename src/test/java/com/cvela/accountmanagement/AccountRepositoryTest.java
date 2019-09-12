package com.cvela.accountmanagement;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cvela.accountmanagement.account.Account;
import com.cvela.accountmanagement.repository.AccountRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private AccountRepository repository;
	
	@Test
	public void testFindByName() {
		Account account = new Account(100, "Account 1", new Date());
		account.setTransactions(null);
		entityManager.persist(account);
		entityManager.flush();
		
		Optional<Account> accountFound = repository.findById(1);
		
		assertThat(accountFound.get().getId() == account.getId());
		
	}

}
