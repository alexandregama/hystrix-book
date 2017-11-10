package com.hystrix.palestra.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class PaymentMoipServiceCommand extends HystrixCommand<Payment> {

	private Order order;
	
	private PaymentMoipApi paymentAPI; 

	protected PaymentMoipServiceCommand(PaymentMoipApi paymentApi, Order order) {
		super(HystrixCommandGroupKey.Factory.asKey("PaymentService"));
		this.paymentAPI = paymentApi;
		this.order = order;
	}

	@Override
	protected Payment run() throws Exception {
		System.out.println("Pagando com Moip");
		
		Payment payment = paymentAPI.pay(order);
		
		return payment;
	}
	
	@Override
	protected Payment getFallback() {
		System.out.println("Pagando com nada! Deu tudo errado na vida!");
		
		return null;
	}

}
