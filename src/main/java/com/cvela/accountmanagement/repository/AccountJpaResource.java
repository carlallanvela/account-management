package com.cvela.accountmanagement.repository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cvela.accountmanagement.account.Account;
import com.cvela.accountmanagement.account.Transaction;
import com.cvela.accountmanagement.exception.AccountNotFoundException;


@RestController
public class AccountJpaResource {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@GetMapping(path="/jpa/accounts")
	public List<Account> retrieveAllAccounts() {
		return accountRepository.findAll();
	}
	
	@GetMapping(path="/jpa/accounts/{id}")
	public Optional<Account> retrieveAccount(@PathVariable int id) {
		Optional<Account> account = accountRepository.findById(id);
		
		if (!account.isPresent()) {
			throw new AccountNotFoundException("id: " + id);
		}

		// HATEOAS
		// enables us to add links using method		
		//"all-users", SERVER_PATH + "/accounts"
		Resource<Account> resource = new Resource<Account>(account.get());
		
		// Enable creates links from methods
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllAccounts());
		
		resource.add(linkTo.withRel("all-users"));
		
		return account;
	}

	@PostMapping("/jpa/accounts")
	public ResponseEntity<Object> createAccount(@Valid @RequestBody Account account) {
		Account savedAccount = accountRepository.save(account);

		// Return a status called CREATED
		// Set a URI of the created Resource to the Response
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedAccount.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/accounts/{id}")
	public void deleteAccount(@PathVariable int id) {
		Account account = accountRepository.getOne(id);
		// Remove Transactions First
		transactionRepository.deleteAll(account.getTransactions());
		accountRepository.deleteById(id);
	}
	
	@GetMapping(path="/jpa/accounts/{id}/transactions")
	public List<Transaction> retrieveAllTransactions(@PathVariable int id) {
		Optional<Account> accountOptional = accountRepository.findById(id);
		if (!accountOptional.isPresent()) {
			throw new AccountNotFoundException("id-" + id);
			
		}
		return accountOptional.get().getTransactions();
	}
	
	@PostMapping("/jpa/accounts/{id}/transactions")
	public ResponseEntity<Object> createTransaction(@PathVariable int id, @RequestBody Transaction transaction) {
		
		Optional<Account> accountOptional = accountRepository.findById(id);
		
		if (!accountOptional.isPresent()) {
			throw new AccountNotFoundException("id-" + id);
		}

		Account account = accountOptional.get();
		transaction.setAccount(account);
		transactionRepository.save(transaction);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(transaction.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
