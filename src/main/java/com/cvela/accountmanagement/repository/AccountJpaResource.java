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
import com.cvela.accountmanagement.exception.TransactionNotFoundException;


@CrossOrigin(origins = "http://account-management-ui.s3-website.us-east-2.amazonaws.com")
//@CrossOrigin(origins = "http://localhost:4200")
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
	
	@GetMapping(path="/jpa/accounts/{accountNumber}")
	public Optional<Account> retrieveAccount(@PathVariable int accountNumber) {
		Optional<Account> account = accountRepository.findById(accountNumber);
		
		if (!account.isPresent()) {
			throw new AccountNotFoundException("accountNumber: " + accountNumber);
		}

		// HATEOAS
		// enables us to add links using method		
		//"all-users", SERVER_PATH + "/accounts"
		Resource<Account> resource = new Resource<Account>(account.get());
		
		// Enable creates links from methods
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllAccounts());
		
		resource.add(linkTo.withRel("all-accounts"));
		
		return account;
	}

	@PostMapping(path="/jpa/accounts")
	public ResponseEntity<Object> createAccount(@Valid @RequestBody Account account) {
		Account savedAccount = accountRepository.save(account);

		// Return a status called CREATED
		// Set a URI of the created Resource to the Response
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{accountNumber}")
			.buildAndExpand(savedAccount.getAccountNumber())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/accounts/{accountNumber}")
	public void deleteAccount(@PathVariable int accountNumber) {
		Account account = accountRepository.getOne(accountNumber);
		// Remove Transactions First
		transactionRepository.deleteAll(account.getTransactions());
		accountRepository.deleteById(accountNumber);
	}
	
	@GetMapping(path="/jpa/accounts/{accountNumber}/transactions")
	public List<Transaction> retrieveAllTransactions(@PathVariable int accountNumber) {
		Optional<Account> accountOptional = accountRepository.findById(accountNumber);
		if (!accountOptional.isPresent()) {
			throw new AccountNotFoundException("accountNumber:" + accountNumber);
		}
		return accountOptional.get().getTransactions();
	}
	
	@GetMapping(path="/jpa/accounts/{accountNumber}/transactions/{transactionId}")
	public Optional<Transaction> retrieveTransaction(@PathVariable int accountNumber,
			@PathVariable int transactionId) {
		Optional<Transaction> transaction = transactionRepository.findById(transactionId);
		
		if (!transaction.isPresent()) {
			throw new TransactionNotFoundException("transactionId: " + transactionId);
		}

		Resource<Transaction> resource = new Resource<Transaction>(transaction.get());
		
		// Enable creates links from methods
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllAccounts());
		
		resource.add(linkTo.withRel("all-transactions"));
		
		return transaction;
	}
	
	@PostMapping(path="/jpa/accounts/{accountNumber}/transactions")
	public ResponseEntity<Object> createTransaction(@PathVariable int accountNumber,
			@RequestBody Transaction transaction) {
		
		Optional<Account> accountOptional = accountRepository.findById(accountNumber);
		
		if (!accountOptional.isPresent()) {
			throw new AccountNotFoundException("accountNumber:" + accountNumber);
		}

		Account account = accountOptional.get();
		transaction.setAccount(account);
		transactionRepository.save(transaction);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{accountNumber}")
			.buildAndExpand(transaction.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/accounts/{accountNumber}/transactions/{transactionId}")
	public void deleteTransaction(@PathVariable int transactionId) {
		transactionRepository.deleteById(transactionId);
	}
}
