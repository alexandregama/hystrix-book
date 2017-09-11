package com.hystrix.examples;

public class UserFallbackService {

	public String call(String username) {
		System.out.println("Fallback User Service..");
		return "Hello " + username;
	}

}
