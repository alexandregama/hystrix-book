package com.hystrix.palestra.observables;

import com.hystrix.palestra.commands.Order;
import com.hystrix.palestra.commands.Payment;
import com.hystrix.palestra.commands.PaymentPayPalApi;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observable;

public class PaymentPayPalObservableCommand extends HystrixObservableCommand<Payment> {

	private PaymentPayPalApi paymentApi;
	
	private Order order;

	protected PaymentPayPalObservableCommand(PaymentPayPalApi paymentApi, Order order) {
		super(Setter
				.withGroupKey(HystrixCommandGroupKey.Factory.asKey("PaymentService"))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
						.withExecutionTimeoutEnabled(false)));
		this.paymentApi = paymentApi;
		this.order = order;
	}

	@Override
	protected Observable<Payment> construct() {
		System.out.println("Paying with PayPal");
		
		Payment payment = paymentApi.pay(order);
		
		Observable<Payment> observable = Observable.just(payment);
		
		return observable;
	}
	
	@Override
	protected Observable<Payment> resumeWithFallback() {
		System.out.println("Callback");
		return Observable.never();
	}

}
