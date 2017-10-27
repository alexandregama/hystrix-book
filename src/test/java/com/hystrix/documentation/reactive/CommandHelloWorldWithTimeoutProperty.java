package com.hystrix.documentation.reactive;

import com.hystrix.book.chapter1.Pause;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class CommandHelloWorldWithTimeoutProperty extends HystrixCommand<String> {

	private String name;

	/**
	 * Configuring the Command to use 2 seconds of timeout
	 */
	public CommandHelloWorldWithTimeoutProperty(String name) {
		super(Setter
				.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandHelloWorldWithTimeoutProperty"))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(2_000))
				);
		this.name = name;
	}

	@Override
	protected String run() throws Exception {
		Pause.waitFor(1);

		return "Hello " + name;
	}

	@Override
	protected String getFallback() {
		return "Hello Fallback Name";
	}

}
