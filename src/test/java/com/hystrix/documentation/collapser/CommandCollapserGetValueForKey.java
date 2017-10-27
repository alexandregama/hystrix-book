package com.hystrix.documentation.collapser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class CommandCollapserGetValueForKey extends HystrixCollapser<List<String>, String, Integer> {

	private Integer key;

	public CommandCollapserGetValueForKey(Integer key) {
		this.key = key;
	}

	@Override
	public Integer getRequestArgument() {
		return key;
	}

	@Override
	protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, Integer>> requests) {
		return new BatchCommand(requests);
	}

	@Override
	protected void mapResponseToRequests(List<String> batchResponse, Collection<CollapsedRequest<String, Integer>> requests) {
		int count = 0;

		for (CollapsedRequest<String, Integer> request : requests) {
			request.setResponse(batchResponse.get(count));
			count++;
		}
	}

	/**
	 * This HystrixCommand will call the dependency system
	 */
	private static final class BatchCommand extends HystrixCommand<List<String>> {

		private final Collection<CollapsedRequest<String, Integer>> requests;

		protected BatchCommand(Collection<CollapsedRequest<String, Integer>> requests) {
			super(Setter
					.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BatchCommandGroupKey"))
					.andCommandKey(HystrixCommandKey.Factory.asKey("BatchCommandKey"))
					.andCommandPropertiesDefaults(HystrixCommandProperties.defaultSetter().withExecutionTimeoutInMilliseconds(30_000)));
			this.requests = requests;
		}

		@Override
		protected List<String> run() throws Exception {
			System.out.println("Calling Api");

			List<String> response = new ArrayList<>();

			requests.forEach(request -> System.out.println(request.getArgument()));

			requests.forEach(request -> response.add("ValueForKey: " + request.getArgument()));

			return response;
		}

		@Override
		protected List<String> getFallback() {
			System.out.println("Fallback Error!");

			return super.getFallback();
		}

	}

}
