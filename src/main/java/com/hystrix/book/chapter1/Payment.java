package com.hystrix.book.chapter1;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class Payment extends HystrixCommand<String> {

	private PaymentPayPalService paymentService;

	public Payment(PaymentPayPalService paymentService) {
		super(HystrixCommandGroupKey.Factory.asKey("PayPal"));
		this.paymentService = paymentService;
	}

	@Override
	protected String run() throws Exception {
		return paymentService.getProvider();
	}

	@Override
	protected String getFallback() {
		return new PaymentServiceStripesFallback().getProvider();
	}

}
