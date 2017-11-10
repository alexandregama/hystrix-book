package com.hystrix.palestra.cache;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class PaymentCommandRequestCache extends HystrixCommand<Boolean> {

	private Integer paymentId;

	protected PaymentCommandRequestCache(Integer paymentId) {
		super(HystrixCommandGroupKey.Factory.asKey("PaymentCommandRequestCache"));
		this.paymentId = paymentId;
	}

	@Override
	protected Boolean run() throws Exception {
		System.out.println("Calculating result for: " + paymentId);

		boolean wasPaid = paymentId % 2 == 0;
		
		return wasPaid;
	}

	@Override
	protected String getCacheKey() {
		return String.valueOf(paymentId);
	}

	@Override
	protected Boolean getFallback() {
		return false;
	}

}
