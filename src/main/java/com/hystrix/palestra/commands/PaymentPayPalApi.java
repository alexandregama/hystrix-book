package com.hystrix.palestra.commands;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PaymentPayPalApi {

	public Payment pay(Order order) {
		int randomNumber = new Random().nextInt();
		if (randomNumber % 2 == 0) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return new Payment(UUID.randomUUID(), order.getPrice());
	}

}
