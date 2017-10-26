package com.hystrix.examples;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HelloWorldFailingWithFallbackCommand extends HystrixCommand<String> {

	private String username;

	public HelloWorldFailingWithFallbackCommand(String username) {
		super(HystrixCommandGroupKey.Factory.asKey(username));
		this.username = username;
	}
	@Override
	protected String run() throws Exception {
		String hello = new UserService().call(username);

		return hello;
	}

	@Override
	protected String getFallback() {
		String hello = new UserFallbackService().call(username);

		return hello;
	}

}

