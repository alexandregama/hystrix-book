package com.hystrix.examples;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import rx.Observable;
import rx.Observer;

public class HystrixObservableCommandTest {

	@Test
	public void shouldExecuteBlocking() throws Exception {
		Observable<String> observable = new HelloWorldCommand("Alexandre Gama").observe();

		String value = observable.toBlocking().single();

		assertThat(value, equalTo("Hello Alexandre Gama"));
	}

	@Test
	public void shouldExecuteAsynchronously() throws Exception {
		Observable<String> observable = new HelloWorldCommand("Alexandre Gama").observe();

		observable.subscribe(new Observer<String>() {

			@Override
			public void onCompleted() {
				System.out.println("On Completed");
			}

			@Override
			public void onError(Throwable e) {

			}

			@Override
			public void onNext(String t) {
				System.out.println("On Next: " + t);
			}
		});

		System.out.println("Waiting 4 seconds before the just() method..");
		TimeUnit.SECONDS.sleep(4);
	}

	@Test
	public void shouldExecuteAsynchronouslyAndThenThrowAnException() throws Exception {
		Observable<String> observable = new HelloWorldFailingCommand("Alexandre Gama").observe();

		observable.subscribe(new Observer<String>() {

			@Override
			public void onCompleted() {
				//This will not be executed because we will receive an error by timeout
				System.out.println("On Completed");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("An error occurred!");
				e.printStackTrace();
			}

			@Override
			public void onNext(String t) {
				System.out.println("On Next: " + t);
			}
		});

		TimeUnit.SECONDS.sleep(4);
	}

}
