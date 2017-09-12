package com.hystrix.book.chapter1;

public class Payment {

	private PaymentPayPalService paymentService;

	public Payment(PaymentPayPalService paymentService) {
		this.paymentService = paymentService;
	}

	public String getPaymentProvider() {
		return paymentService.getProvider();
	}

}
