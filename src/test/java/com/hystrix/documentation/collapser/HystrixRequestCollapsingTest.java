package com.hystrix.documentation.collapser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.concurrent.Future;

import org.junit.Test;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

public class HystrixRequestCollapsingTest {

	@Test
	public void shouldCallRequestsUsingRequestCollapsing() throws Exception {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		try {
			Future<String> f1 = new CommandCollapserGetValueForKey(1).queue();
			Future<String> f2 = new CommandCollapserGetValueForKey(2).queue();
			Future<String> f3 = new CommandCollapserGetValueForKey(3).queue();
			Future<String> f4 = new CommandCollapserGetValueForKey(4).queue();

			assertThat(f1.get(), equalTo("ValueForKey: 1"));
			assertThat(f2.get(), equalTo("ValueForKey: 2"));
			assertThat(f3.get(), equalTo("ValueForKey: 3"));
			assertThat(f4.get(), equalTo("ValueForKey: 4"));
		} finally {
			context.close();
		}
	}

}
