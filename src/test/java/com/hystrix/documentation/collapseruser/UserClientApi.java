package com.hystrix.documentation.collapseruser;

import com.hystrix.documentation.collapseruser.rest.User;
import com.hystrix.documentation.collapseruser.rest.UserRepository;
import com.hystrix.documentation.collapseruser.rest.Users;

public class UserClientApi {

	private Users repository = new UserRepository();

	public User findUserBy(Long id) {
		return repository.findBy(id);
	}

}
