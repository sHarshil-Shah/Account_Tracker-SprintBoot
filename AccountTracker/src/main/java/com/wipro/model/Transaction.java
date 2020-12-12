package com.wipro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {

	@JsonProperty(required = true)
	private double amount;

	@JsonProperty(required = true)
	private Long fromAccountId;

	@JsonProperty(required = true)
	private Long toAccountId;

	public Transaction() {
	}

	public Transaction(double amount, Long fromAccountId, Long toAccountId) {
		this.amount = amount;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
	}

	public double getAmount() {
		return amount;
	}

	public Long getFromAccountId() {
		return fromAccountId;
	}

	public Long getToAccountId() {
		return toAccountId;
	}
}
