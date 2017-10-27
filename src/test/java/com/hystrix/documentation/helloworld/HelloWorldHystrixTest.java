package com.hystrix.documentation.helloworld;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.concurrent.Future;

import org.junit.Test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.exception.HystrixRuntimeException;

public class HelloWorldHystrixTest {

	@Test
	public void shouldUseHystrixSynchronously() throws Exception {
		HystrixCommand<String> command = new CommandHelloWorldWithoutFallback("Alexandre Gama");

		String name = command.execute();

		assertThat(name, equalTo("Hello Alexandre Gama"));
	}

	@Test(expected = HystrixRuntimeException.class)
	public void shouldUseHystrixWithTimeoutWithoutFallbackSynchronously() throws Exception {
		HystrixCommand<String> command = new CommandHelloWorldWithTimeoutWithoutFallback("Alexandre Gama");

		command.execute();
	}

	@Test
	public void shouldUseHystrixWithTimeoutWithFallbackSynchronoysly() throws Exception {
		HystrixCommand<String> command = new CommandHelloWorldWithTimeoutWithFallback("Alexandre Gama");

		String name = command.execute();

		assertThat(name, equalTo("Hello Fallback Name"));
	}

	@Test
	public void shouldUseHystrixAsynchronously() throws Exception {
		HystrixCommand<String> command = new CommandHelloWorldWithoutFallback("Alexandre Gama");

		Future<String> future = command.queue();

		System.out.println("Executing another action..");

		String name = future.get();

		System.out.println("Getting the name: " + name);

		assertThat(name, equalTo("Hello Alexandre Gama"));
	}

}
