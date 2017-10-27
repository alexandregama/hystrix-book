package com.hystrix.documentation.cache;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

public class CommandHystrixRequestCacheTest {

	/**
	 * Notice that we gain a specific Cache Key when we calculate the value
	 */
	@Test
	public void shouldGetValueFromCache() throws Exception {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		try {
			assertThat(new CommandRequestCache(10).execute(), equalTo(true));
			assertThat(new CommandRequestCache(16).execute(), equalTo(true));
			assertThat(new CommandRequestCache(20).execute(), equalTo(true));

			//This calculation should not ocurr because we already calculated it before
			assertThat(new CommandRequestCache(10).execute(), equalTo(true));
			assertThat(new CommandRequestCache(16).execute(), equalTo(true));
			assertThat(new CommandRequestCache(20).execute(), equalTo(true));
		} finally {
			context.close();
		}
	}

	@Test
	public void shouldGetValueFromCacheAndIndicateItFromHystrix() throws Exception {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		try {
			HystrixCommand<Boolean> firstCommand = new CommandRequestCache(10);

			assertThat(firstCommand.execute(), equalTo(true));
			assertThat(firstCommand.isResponseFromCache(), equalTo(false));

			HystrixCommand<Boolean> secondCommand = new CommandRequestCache(10);

			assertThat(secondCommand.execute(), equalTo(true));
			assertThat(secondCommand.isResponseFromCache(), equalTo(true)); //here we have a hit on Cache
		} finally {
			context.close();
		}
	}

	@Test
	public void shouldGetValueFromCacheAndIndicateItFromHystrixButAnotherContext() throws Exception {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();

		try {
			HystrixCommand<Boolean> firstCommand = new CommandRequestCache(10);

			assertThat(firstCommand.execute(), equalTo(true));
			assertThat(firstCommand.isResponseFromCache(), equalTo(false));

			HystrixCommand<Boolean> secondCommand = new CommandRequestCache(10);

			assertThat(secondCommand.execute(), equalTo(true));
			assertThat(secondCommand.isResponseFromCache(), equalTo(true)); //hit on the cache

			//Initializing the Context again
			context = HystrixRequestContext.initializeContext();

			HystrixCommand<Boolean> thirdCommand = new CommandRequestCache(10);

			assertThat(thirdCommand.execute(), equalTo(true));
			assertThat(thirdCommand.isResponseFromCache(), equalTo(false)); //we con't have a hit on cache since we are using another HystrixRequestContext
		} finally {
			context.close();
		}
	}

}
