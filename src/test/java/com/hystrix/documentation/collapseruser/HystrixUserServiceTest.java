package com.hystrix.documentation.collapseruser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.concurrent.Future;

import org.junit.Test;

import com.hystrix.documentation.collapseruser.rest.User;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

public class HystrixUserServiceTest {

	@Test
	public void shouldCallTheUserApiWithRequestCollapser() throws Exception {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		try {
			Future<User> f1 = new HystrixUserCommandCollapser(1L).queue();
			Future<User> f2 = new HystrixUserCommandCollapser(2L).queue();
			Future<User> f3 = new HystrixUserCommandCollapser(3L).queue();
			Future<User> f4 = new HystrixUserCommandCollapser(4L).queue();

			assertThat(f1.get().getName(), equalTo("Alexandre Gama"));
			assertThat(f2.get().getName(), equalTo("Bruna Catocci"));
			assertThat(f3.get().getName(), equalTo("Celia Regina"));
			assertThat(f4.get().getName(), equalTo("Marcia Gama"));

		} finally {
			context.close();
		}
	}

}
