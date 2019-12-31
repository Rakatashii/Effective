package com.revature.effective.nintotwelve.multithreading;

public class LockObject {

	private final Object lock = new Object();
	
	public static void main(String... args) {
		
	}
	
	public void holdLock() {
		synchronized(lock) {
			System.out.println("Nothing in here can be accessed externally");
		}
	}
}
