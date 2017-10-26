package com.hystrix.examples;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HelloWorldFailingCommand extends HystrixCommand<String> {

	private String username;

	protected HelloWorldFailingCommand(String username) {
		super(HystrixCommandGroupKey.Factory.asKey(username));
		this.username = username;
	}

	@Override
	protected String run() throws Exception {
		String hello = new UserService().call(username);

		return hello;
	}

}
