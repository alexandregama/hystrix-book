package com.hystrix.palestra.commands;

import java.util.UUID;

public class Payment {

	private UUID id;
	
	private Integer value;

	public Payment(UUID id, Integer value) {
		this.id = id;
		this.value = value;
	}

	public UUID getId() {
		return id;
	}

	public Integer getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", value=" + value + "]";
	}

}
