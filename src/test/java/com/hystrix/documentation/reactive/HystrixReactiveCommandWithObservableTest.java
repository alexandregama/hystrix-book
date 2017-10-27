package com.hystrix.documentation.reactive;

import org.junit.Test;

import com.hystrix.book.chapter1.Pause;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observer;

public class HystrixReactiveCommandWithObservableTest {

	@Test
	public void shouldUseHystrixObservableCommandWithSuccess() throws Exception {
		HystrixObservableCommand<String> command = new CommandHelloWorldObservable("Alexandre Gama");

		command
			.observe()
			.subscribe(new Observer<String>() {

				@Override
				public void onCompleted() {
					System.out.println("On Completed!");
				}

				@Override
				public void onError(Throwable e) {
					System.out.println("An error ocurred!");
					e.printStackTrace();
				}

				@Override
				public void onNext(String value) {
					System.out.println("Value received: " + value);
				}
			});

		System.out.println("Executing Another Action...on Thread: " + Thread.currentThread().getName());

		Pause.waitFor(3);
	}

}
