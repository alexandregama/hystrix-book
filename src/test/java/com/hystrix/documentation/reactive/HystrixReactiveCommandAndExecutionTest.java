package com.hystrix.documentation.reactive;

import org.junit.Test;

import com.hystrix.book.chapter1.Pause;
import com.netflix.hystrix.HystrixCommand;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

public class HystrixReactiveCommandAndExecutionTest {

	/**
	 * In this example we are using an Hot Observable that executes the command immediately
	 */
	@Test
	public void shouldObserveHystrixCommandAsynchronously() throws Exception {
		HystrixCommand<String> command = new CommandHelloWorld("Alexandre Gama");

		Observable<String> observable = command.observe();

		observable.subscribe(new Action1<String>() {

			@Override
			public void call(String name) {
				System.out.println("Hello " + name);
			}
		});

		//This Pause is necessary because we're executing the Hystrix Command asynchronously
		Pause.waitFor(1);
	}

	@Test
	public void shouldObserveHystrixCommandAsyncrhnouslyWithLambda() throws Exception {
		HystrixCommand<String> command = new CommandHelloWorld("Alexandre Gama");

		command
			.observe()
			.subscribe(name -> {
				System.out.println("Hello " + name);
			});

		Pause.waitFor(1);
	}

	/**
	 * Using Action1 we are ignoring onError() and onCompleted() methods
	 */
	@Test
	public void shouldObserveHystrixCommandAsyncrhnouslyWithLambdaAndTimeout() throws Exception {
		HystrixCommand<String> command = new CommandHelloWorldWithTimeoutProperty("Alexandre Gama");

		command
			.observe()
			.subscribe(name -> {
				System.out.println("Hello " + name);
			});

		System.out.println("Executing another Action..");

		Pause.waitFor(3);
	}

	/**
	 * Using Observer we will not ignore onError() and onCompleted() methods
	 */
	@Test
	public void shouldUseObservableMethods() throws Exception {
		HystrixCommand<String> command = new CommandHelloWorldWithTimeoutProperty("Alexandre Gama");

		command
			.observe()
			.subscribe(new Observer<String>() {

				@Override
				public void onCompleted() {
					System.out.println("Task Completed!");
				}

				@Override
				public void onError(Throwable e) {
					System.out.println("An error occurred!");
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
