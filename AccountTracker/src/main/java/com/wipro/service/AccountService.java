package com.wipro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wipro.model.Account;
import com.wipro.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountrepository;

	public List<Account> getAllAccounts() {

		List<Account> accounts = new ArrayList<Account>();
		accountrepository.findAll().forEach(accounts1 -> accounts.add(accounts1));
		return accounts;
	}

	public Account getAccountById(long id) {
		return accountrepository.findById(id).get();
	}

//saving a specific record by using the method save() of CrudRepository  
	public void saveOrUpdate(Account account) {
		accountrepository.save(account);
	}

//deleting a specific record by using the method deleteById() of CrudRepository  
	public void delete(long id) {
		accountrepository.deleteById(id);
	}

//updating a record  
	public void update(Account account, int accountid) {
		accountrepository.save(account);
	}

}
