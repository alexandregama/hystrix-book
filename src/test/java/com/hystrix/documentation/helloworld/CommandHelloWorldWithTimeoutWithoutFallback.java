package com.hystrix.documentation.helloworld;

import com.hystrix.book.chapter1.Pause;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandHelloWorldWithTimeoutWithoutFallback extends HystrixCommand<String> {

	private String name;

	public CommandHelloWorldWithTimeoutWithoutFallback(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("CommandHelloWorldWithTimeout"));
		this.name = name;
	}

	@Override
	protected String run() throws Exception {
		Pause.waitFor(5);

		return "Hello " + name;
	}

}
