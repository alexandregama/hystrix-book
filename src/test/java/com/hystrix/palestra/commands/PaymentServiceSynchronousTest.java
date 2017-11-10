package com.hystrix.palestra.commands;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Before;
import org.junit.Test;

import com.hystrix.palestra.commands.Order;
import com.hystrix.palestra.commands.Payment;
import com.hystrix.palestra.commands.PaymentPayPalApi;
import com.hystrix.palestra.commands.PaymentPayPalServiceCommand;
import com.hystrix.palestra.commands.PaymentPayPalServiceCommandWithFallabck;
import com.netflix.hystrix.HystrixCommand;

public class PaymentServiceSynchronousTest {
	
	private PaymentPayPalApi paymentApi;
	
	@Before
	public void setUp() {
		this.paymentApi = new PaymentPayPalApi();
	}
	
	@Test
	public void shouldInvokeServiceSynchronouslyWithoutFallback() throws Exception {
		Order order = new Order(10L, 100);
		
		HystrixCommand<Payment> paymentService = new PaymentPayPalServiceCommand(paymentApi, order);
		
		Payment payment = paymentService.execute();
		
		assertThat(payment.getId(), notNullValue());
	}
	
	@Test
	public void shouldInvokeServiceSynchronouslyWithFallback() throws Exception {
		Order order = new Order(10L, 100);
		
		HystrixCommand<Payment> paymentService = new PaymentPayPalServiceCommandWithFallabck(paymentApi, order);
		
		Payment payment = paymentService.execute();
		
		assertThat(payment.getId(), notNullValue());
	}

}
