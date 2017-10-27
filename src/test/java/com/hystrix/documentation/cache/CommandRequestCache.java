package com.hystrix.documentation.cache;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandRequestCache extends HystrixCommand<Boolean> {

	private Integer value;

	protected CommandRequestCache(Integer value) {
		super(HystrixCommandGroupKey.Factory.asKey("CommandRequestCache"));
		this.value = value;
	}

	@Override
	protected Boolean run() throws Exception {
		System.out.println("Calculating result for: " + value);

		return value == 0 || value % 2 == 0;
	}

	@Override
	protected String getCacheKey() {
		return String.valueOf(value);
	}

	@Override
	protected Boolean getFallback() {
		return false;
	}

}
