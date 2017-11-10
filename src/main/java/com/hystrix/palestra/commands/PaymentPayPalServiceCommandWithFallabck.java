package com.hystrix.palestra.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class PaymentPayPalServiceCommandWithFallabck extends HystrixCommand<Payment> {

	private Order order;
	
	private PaymentPayPalApi paymentAPI; 

	protected PaymentPayPalServiceCommandWithFallabck(PaymentPayPalApi paymentApi, Order order) {
		super(HystrixCommandGroupKey.Factory.asKey("PaymentService"));
		this.paymentAPI = paymentApi;
		this.order = order;
	}

	@Override
	protected Payment run() throws Exception {
		System.out.println("Pagando com PayPal");
		
		Payment payment = paymentAPI.pay(order);
		
		return payment;
	}
	
	@Override
	protected Payment getFallback() {
		PaymentMoipApi paymentMoipApi = new PaymentMoipApi();
		
		PaymentMoipServiceCommand moipServiceCommand = new PaymentMoipServiceCommand(paymentMoipApi, order);
		
		return moipServiceCommand.execute();
	}

}
