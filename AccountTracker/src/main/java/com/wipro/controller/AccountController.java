package com.wipro.controller;

import com.wipro.exception.ResourceNotFoundException;
import com.wipro.model.Account;
import com.wipro.model.Transaction;
import com.wipro.repository.AccountRepository;
import com.wipro.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/customers/{customerId}/accounts")
	public Page<Account> getAllAccountsByCustomerId(@PathVariable(value = "customerId") Long customerId,
			Pageable pageable) {
		return accountRepository.findByCustId(customerId, pageable);
	}

	@PostMapping("/customers/{customerId}/accounts")
	public Account createAccount(@PathVariable(value = "customerId") Long customerId,
			@Valid @RequestBody Account account) {
		return customerRepository.findById(customerId).map(customer -> {
			account.setCust(customer);
			return accountRepository.save(account);
		}).orElseThrow(() -> new ResourceNotFoundException("CustomerId " + customerId + " not found"));
	}

	@PutMapping("/customers/{customerId}/accounts/{accountId}")
	public Account updateAccount(@PathVariable(value = "customerId") Long customerId,
			@PathVariable(value = "accountId") Long accountId, @Valid @RequestBody Account accountRequest) {
		if (!customerRepository.existsById(customerId)) {
			throw new ResourceNotFoundException("CustomerId " + customerId + " not found");
		}

		return accountRepository.findById(accountId).map(account -> {
			account.setAccType(accountRequest.getAccType());
			account.setFund(accountRequest.getFund());
			return accountRepository.save(account);
		}).orElseThrow(() -> new ResourceNotFoundException("AccountId " + accountId + "not found"));
	}

	@PutMapping("/transfer")
	public ResponseEntity<?> transferMoney(@Valid @RequestBody Transaction tranReq) {

		long from = tranReq.getFromAccountId();
		long to = tranReq.getToAccountId();
		double amount = tranReq.getAmount();

		if (!accountRepository.existsById(from))
			throw new ResourceNotFoundException("AccountId " + from + " not found");
		if (!accountRepository.existsById(to))
			throw new ResourceNotFoundException("AccountId " + to + " not found");

		accountRepository.findById(from).map(account -> {
			if (account.getFund() < amount)
				throw new ResourceNotFoundException("Account has not enough fund\n\n");
			account.setAccType(account.getAccType());
			account.setFund(account.getFund() - amount);
			return accountRepository.save(account);
		});

		accountRepository.findById(to).map(account -> {
			account.setAccType(account.getAccType());
			account.setFund(account.getFund() + amount);
			return accountRepository.save(account);
		});

		return ResponseEntity.ok().build();

	}

	@DeleteMapping("/customers/{customerId}/accounts/{accountId}")
	public ResponseEntity<?> deleteAccount(@PathVariable(value = "customerId") Long customerId,
			@PathVariable(value = "accountId") Long accountId) {
		return accountRepository.findByIdAndCustId(accountId, customerId).map(account -> {
			accountRepository.delete(account);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(
				"Account not found with id " + accountId + " and customerId " + customerId));
	}
}