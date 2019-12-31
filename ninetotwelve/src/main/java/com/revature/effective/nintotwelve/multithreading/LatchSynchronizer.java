package com.revature.effective.nintotwelve.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LatchSynchronizer {
	
	public static void main(String[] args) throws InterruptedException {
		int concurrency = Runtime.getRuntime().availableProcessors();
		double actionTime = 0.0;
		try {
			actionTime = time(Executors.newFixedThreadPool(concurrency*2), concurrency, () -> {
				System.out.println("Thread [main] - " + Thread.currentThread().getName());
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Time taken - " + actionTime + " seconds");
	}
	
	public static double time(Executor executor, int concurrency, 
			Runnable action) throws InterruptedException {
		
		System.out.println("Starting...");
		
		CountDownLatch ready = new CountDownLatch(concurrency);
		CountDownLatch start = new CountDownLatch(1);
		CountDownLatch done = new CountDownLatch(concurrency);
		
		for (int i = 0; i < concurrency; i++) {
			executor.execute(() -> {
				ready.countDown();
				try {
					// only called once, beginning the back and forth between ready and done
					start.await(); // thread will pause here until start.countDown() is called
					action.run();
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("INTERRUPTING From Thread - " + Thread.currentThread().getName());
					Thread.currentThread().interrupt();
				} finally {
					done.countDown();
				}
			});
		}
		
		ready.await(); // thread will pause here until ready.countDown() is called
		long startNanos = System.nanoTime();
		start.countDown();
		done.await(); // thread now pauses unril the for loop is finished
		// when done has counted to 0, the method will return
		
		System.out.println("Thread [done in meth] - " + Thread.currentThread().getName());
		
		return (double) (System.nanoTime() - startNanos) / 100000000;
	}
}
