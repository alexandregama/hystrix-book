package com.hystrix.examples;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * Every class that worry about its execution should extends the HystrixCommand
 *
 * The method run() will be used to run the dangerous implementation
 */
public class HelloWorldCommand extends HystrixCommand<String> {

	private String username;

	protected HelloWorldCommand(String username) {
		super(HystrixCommandGroupKey.Factory.asKey("SimpleExampleGroup"));
		this.username = username;
	}

	@Override
	protected String run() throws Exception {
		return "Hello " + username;
	}

}
