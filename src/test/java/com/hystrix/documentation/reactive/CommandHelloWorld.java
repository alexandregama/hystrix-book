package com.hystrix.documentation.reactive;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandHelloWorld extends HystrixCommand<String> {

	private String name;

	public CommandHelloWorld(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("CommandHelloWorld"));
		this.name = name;
	}

	@Override
	protected String run() throws Exception {
		return "Hello " + name;
	}

}
