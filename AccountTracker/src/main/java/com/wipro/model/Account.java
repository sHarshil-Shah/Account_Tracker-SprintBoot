package com.wipro.model;

import javax.persistence.*;
import javax.persistence.Entity;
import org.hibernate.annotations.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String accType;

	private double fund;

	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Customer cust;

	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(long id, String accType, double fund, Customer cust) {
		super();
		this.id = id;
		this.accType = accType;
		this.fund = fund;
		this.cust = cust;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public double getFund() {
		return fund;
	}

	public void setFund(double fund) {
		this.fund = fund;
	}

}
