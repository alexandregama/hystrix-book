package com.hystrix.book.chapter1;

import java.util.concurrent.TimeUnit;

public class Pause {

	public static void waitFor(int timeInSeconds) {
		try {
			TimeUnit.SECONDS.sleep(timeInSeconds);
		} catch (InterruptedException e) {
			throw new RuntimeException("Error on Pause");
		}
	}

}
