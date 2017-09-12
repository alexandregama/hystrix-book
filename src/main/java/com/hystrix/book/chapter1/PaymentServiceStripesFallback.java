package com.hystrix.book.chapter1;

public class PaymentServiceStripesFallback {

	public String getProvider() {
		return "Stripes";
	}
}
