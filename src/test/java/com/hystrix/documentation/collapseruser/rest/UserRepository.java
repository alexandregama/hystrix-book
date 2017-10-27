package com.hystrix.documentation.collapseruser.rest;

import java.util.HashMap;
import java.util.Map;

public class UserRepository implements Users {

	private Map<Long, User> dabatase = new HashMap<>();

	public UserRepository() {
		this.dabatase.put(1L, new User(1L, "Alexandre Gama"));
		this.dabatase.put(2L, new User(2L, "Bruna Catocci"));
		this.dabatase.put(3L, new User(3L, "Celia Regina"));
		this.dabatase.put(4L, new User(4L, "Marcia Gama"));
	}

	@Override
	public User findBy(Long id) {
		return this.dabatase.get(id);
	}

}
