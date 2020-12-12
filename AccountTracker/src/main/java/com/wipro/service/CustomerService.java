package com.wipro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wipro.model.Customer;
import com.wipro.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerrepository;

	public List<Customer> getAllCustomers() {

		List<Customer> customers = new ArrayList<Customer>();
		customerrepository.findAll().forEach(customers1 -> customers.add(customers1));
		return customers;
	}

	public Customer getCustomerById(long id) {
		return customerrepository.findById(id).get();
	}

//saving a specific record by using the method save() of CrudRepository  
	public void saveOrUpdate(Customer customer) {
		customerrepository.save(customer);
	}

//deleting a specific record by using the method deleteById() of CrudRepository  
	public void delete(long id) {
		customerrepository.deleteById(id);
	}

//updating a record  
	public void update(Customer customer, int customerid) {
		customerrepository.save(customer);
	}

}
