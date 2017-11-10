package com.hystrix.palestra.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class PaymentPayPalServiceCommandMoreTimeout extends HystrixCommand<Payment> {

	private Order order;
	
	private PaymentPayPalApi paymentAPI; 

	protected PaymentPayPalServiceCommandMoreTimeout(PaymentPayPalApi paymentApi, Order order) {
		super(Setter
				.withGroupKey(HystrixCommandGroupKey.Factory.asKey("PaymentService"))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
						.withExecutionTimeoutInMilliseconds(5)));
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
