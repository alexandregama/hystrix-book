package com.hystrix.examples;

import java.util.concurrent.TimeUnit;

class UserService {

	public String call(String username) {
		try {
			TimeUnit.SECONDS.sleep(2);
			System.out.println("Calling the WebService");
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted by the Hystrix Thread because we don't have a fallback implemented");
			e.printStackTrace();
		}
		return "Hello " + username;
	}

}
