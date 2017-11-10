package com.hystrix.palestra.commands;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;

import com.netflix.hystrix.HystrixCommand;

public class PaymentServiceAsynchronousTest {
	
	private PaymentPayPalApi paymentApi;
	
	@Before
	public void setUp() {
		this.paymentApi = new PaymentPayPalApi();
	}
	
	@Test
	public void shouldInvokeServiceAsynchronously() throws Exception {
		Order order = new Order(10L, 100);
		
		HystrixCommand<Payment> paymentService = new PaymentPayPalServiceCommand(paymentApi, order);
		
		Future<Payment> future = paymentService.queue();
		
		System.out.println("Fazendo outra coisa legal enquanto paga..");
		
		Payment payment = future.get();
		
		assertThat(payment.getId(), notNullValue());
	}
	
}
