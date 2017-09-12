package com.hystrix.examples;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.concurrent.Future;

import org.junit.Test;

import com.netflix.hystrix.HystrixCommand;

public class HystrixAsynchronousHelloWorldTest {

	/**
	 * In this example we're calling the method queue() that returns a Future.
	 * With the Future in our hands we can call the method get() to return the value.
	 * Rememeber that this method is blocking
	 */
	@Test
	public void shouldCallTheMethodAsynchronously() throws Exception {
		HystrixCommand<String> command = new HelloWorldCommand("Alexandre Gama");

		Future<String> future = command.queue();

		System.out.println("Doing something before the result");

		String hello = future.get();

		System.out.println(hello);

		assertThat(hello, equalTo("Hello Alexandre Gama"));
	}

}
