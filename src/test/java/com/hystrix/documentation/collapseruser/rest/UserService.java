package com.hystrix.documentation.collapseruser.rest;

import com.hystrix.documentation.collapseruser.UserClientApi;

public class UserService {

	private UserClientApi client = new UserClientApi();

	public User findBy(Long id) {
		return client.findUserBy(id);
	}

}
