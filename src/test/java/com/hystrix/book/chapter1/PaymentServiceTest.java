package com.hystrix.book.chapter1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PaymentServiceTest {

	@Test
	public void shouldCallThePaymentServiceDefault() throws Exception {
		PaymentPayPalService paymentService = new PaymentPayPalService();

		Payment payment = new Payment(paymentService);

		String paymentProvider = payment.getPaymentProvider();

		assertEquals("PayPal", paymentProvider);
	}

}
