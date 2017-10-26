package com.hystrix.documentation.helloworld;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandHelloWorldWithoutFallback extends HystrixCommand<String> {

	private String name;

	protected CommandHelloWorldWithoutFallback(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("CommandHelloWorld"));
		this.name = name;
	}

	@Override
	protected String run() throws Exception {
		return "Hello " + name;
	}

}
