package com.hystrix.book.chapter1;

public class PaymentPayPalService {

	public String getProvider() {
		Pause.waitFor(5);

		return "PayPal";
	}

}
