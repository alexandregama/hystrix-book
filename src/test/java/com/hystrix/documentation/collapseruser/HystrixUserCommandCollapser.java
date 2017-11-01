package com.hystrix.documentation.collapseruser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hystrix.documentation.collapseruser.rest.User;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class HystrixUserCommandCollapser extends HystrixCollapser<List<User>, User, Long> {

	private Long userId;

	public HystrixUserCommandCollapser(Long userId) {
		this.userId = userId;
	}

	@Override
	public Long getRequestArgument() {
		return userId;
	}

	@Override
	protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, Long>> requests) {
		System.out.println("Requests while creating Command: ");

		requests.forEach(request -> {
			Long id = request.getArgument();
			System.out.println(id);
		});

		return new BatchCommand(requests);
	}

	@Override
	protected void mapResponseToRequests(List<User> batchResponse, Collection<CollapsedRequest<User, Long>> requests) {
		requests.forEach(request -> {
			Long argument = request.getArgument();
			User user = batchResponse.get(argument.intValue());
			request.setResponse(user);
		});
	}

	private static final class BatchCommand extends HystrixCommand<List<User>> {

		private final Collection<com.netflix.hystrix.HystrixCollapser.CollapsedRequest<User, Long>> requests;

		private final UserClientApi clientApi = new UserClientApi();

		public BatchCommand(Collection<CollapsedRequest<User, Long>> requests) {
			super(Setter
					.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BatchCommandGroupKey"))
					.andCommandKey(HystrixCommandKey.Factory.asKey("BatchCommandKey"))
					.andCommandPropertiesDefaults(HystrixCommandProperties
							.defaultSetter()
							.withExecutionTimeoutInMilliseconds(30_000)));
			this.requests = requests;
		}

		@Override
		protected List<User> run() throws Exception {
			List<User> usersFound = new ArrayList<>();

			System.out.println("Calling UserClientApi");
			requests.forEach(request -> {
				User user = clientApi.findUserBy(request.getArgument());
				usersFound.add(user);
			});

			return usersFound;
		}
	}

}
