package com.hystrix.examples;

import org.junit.Test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.exception.HystrixRuntimeException;

public class HystrixHelloWorldTest {

	/**
	 * This test will pass because the service is responsible
	 */
	@Test
	public void shouldCallSimpleCommand() throws Exception {
		HystrixCommand<String> command = new HelloWorldCommand("Alexandre Gama");

		command.execute();
	}

	/**
	 * This test will not pass since the service is executing in 2 seconds, more than
	 * the time accepted by Hystrix Command.
	 *
	 * In this case we should treat the exception, without throwing it. We can achieve that implementing the Fallback
	 */
	@Test(expected = HystrixRuntimeException.class)
	public void shouldCallCommandThatWillFailByTimeout() throws Exception {
		HystrixCommand<String> command = new HelloWorldFailingCommand("Alexandre Gama");

		command.execute();
	}

	/**
	 * Important here: In this method, the UserService will fail but now the Command has the getFallback() method
	 * implemented.
	 *
	 * When Hystrix throws an exception because the timeout, the Hystrix Thread will kill the Thread with the
	 * failing method (with the Sleep) and will call the Fallback implementation, in this case calling the
	 * UserFallbackService class
	 *
	 * This is important because Hystrix does not wait to the failling method to be finished. Hystrix fail fast in
	 * this case
	 */
	@Test
	public void shouldCallCommandWithFallback() throws Exception {
		HystrixCommand<String> command = new HelloWorldFailingWithFallbackCommand("Alexandre Gama");

		command.execute();
	}

}
