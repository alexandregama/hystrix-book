package com.hystrix.documentation.reactive.copy;

import com.hystrix.book.chapter1.Pause;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observable;

public class CommandHelloWorldObservable extends HystrixObservableCommand<String> {

	private String name;

	/**
	 * Configuring the Command to use 2 seconds of timeout
	 */
	public CommandHelloWorldObservable(String name) {
		super(Setter
				.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ComandHelloWorldObservable"))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(4_000)));
		this.name = name;
	}

	@Override
	protected Observable<String> construct() {
		System.out.println("Executing Thread: " + Thread.currentThread().getName());

		Pause.waitFor(3);

		return Observable.just("Hello " + name);
	}

	@Override
	protected Observable<String> resumeWithFallback() {
		return Observable.just("Fallback Name");
	}

}
