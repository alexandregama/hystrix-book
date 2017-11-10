	package com.hystrix.palestra.observables;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.hystrix.palestra.commands.Order;
import com.hystrix.palestra.commands.Payment;
import com.hystrix.palestra.commands.PaymentPayPalApi;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

public class PaymentServiceAsynchronousTest {
	
	private PaymentPayPalApi paymentApi;
	
	@Before
	public void setUp() {
		this.paymentApi = new PaymentPayPalApi();
	}
	
	@Test
	public void shouldInvokeServiceAsynchronouslyAndReactive() throws Exception {
		Order order = new Order(10L, 100);
		
		HystrixObservableCommand<Payment> command = new PaymentPayPalObservableCommand(paymentApi, order);
		
		Observable<Payment> observable = command.observe();
		
		observable
			.subscribe(new Action1<Payment>() {

				@Override
				public void call(Payment payment) {
					System.out.println("Pagamento foi feito! " + payment);
				}
			});
		
		TimeUnit.SECONDS.sleep(3);
	}
	
	@Test
	public void shouldInvokeServiceAsynchronouslyAndReactiveMoreComplete() throws Exception {
		Order order = new Order(10L, 100);
		
		HystrixObservableCommand<Payment> command = new PaymentPayPalObservableCommand(paymentApi, order);
		
		Observable<Payment> observable = command.observe();
		
		observable
			.subscribe(new Observer<Payment>() {
	
				@Override
				public void onCompleted() {
					System.out.println("Pagamento finalizado!");
				}
	
				@Override
				public void onError(Throwable e) {
					System.out.println("Deu zica!");
				}
	
				@Override
				public void onNext(Payment payment) {
					System.out.println("Proxima task com o Pagamento: " + payment);
				}
			});
		
		TimeUnit.SECONDS.sleep(3);
	}
	
}
