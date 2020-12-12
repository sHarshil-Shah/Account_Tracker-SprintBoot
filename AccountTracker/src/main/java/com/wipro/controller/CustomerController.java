package com.wipro.controller;

import com.wipro.exception.ResourceNotFoundException;
import com.wipro.model.Customer;
import com.wipro.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping("/")
	public @ResponseBody String greeting() {
		return "Hello, World";
	}

	@GetMapping("/customers")
	public List<Customer> getAllCustomers(Pageable pageable) {
		return customerRepository.findAll();
	}

	@PostMapping("/customer")
	public Customer createCustomer(@Valid @RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	@PutMapping("/customers/{customerId}")
	public Customer updateCustomer(@PathVariable Long customerId, @Valid @RequestBody Customer customerRequest) {
		return customerRepository.findById(customerId).map(customer -> {
			customer.setName(customerRequest.getName());
			return customerRepository.save(customer);
		}).orElseThrow(() -> new ResourceNotFoundException("CustomerId " + customerId + " not found"));
	}

	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
		return customerRepository.findById(customerId).map(customer -> {
			customerRepository.delete(customer);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("CustomerId " + customerId + " not found"));
	}

	Optional<Customer> findById(Long id) {
		return customerRepository.findById(id);
	}
}