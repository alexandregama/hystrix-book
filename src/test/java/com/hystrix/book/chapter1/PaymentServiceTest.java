package com.hystrix.book.chapter1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.netflix.hystrix.HystrixCommand;

public class PaymentServiceTest {

	@Test
	public void shouldCallThePaymentServiceDefault() throws Exception {
		PaymentPayPalService paymentService = new PaymentPayPalService();

		HystrixCommand<String> payment = new Payment(paymentService);

		String paymentProvider = payment.execute();

		assertEquals("PayPal", paymentProvider);
	}

}
