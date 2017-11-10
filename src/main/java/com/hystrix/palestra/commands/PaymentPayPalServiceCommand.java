package com.hystrix.palestra.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class PaymentPayPalServiceCommand extends HystrixCommand<Payment> {

	private Order order;
	
	private PaymentPayPalApi paymentAPI; 

	public PaymentPayPalServiceCommand(PaymentPayPalApi paymentApi, Order order) {
		super(HystrixCommandGroupKey.Factory.asKey("PaymentService"));
		this.paymentAPI = paymentApi;
		this.order = order;
	}

	@Override
	protected Payment run() throws Exception {
		System.out.println("Paying with PayPal");
		
		Payment payment = paymentAPI.pay(order);
		
		return payment;
	}

}
