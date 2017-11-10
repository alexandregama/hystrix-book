package com.hystrix.palestra.commands;

public class Order {

	private Long id;
	
	private Integer price;

	public Order(Long id, Integer price) {
		this.id = id;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public Integer getPrice() {
		return price;
	}

}
