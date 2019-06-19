package com.revature.effective.reactive.multicasting_operations.replay;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MulticastReplay {
	public static void main(String[] argS) throws InterruptedException {
		Observable<Long> source = Observable.interval(1L, TimeUnit.SECONDS).take(6).replay().autoConnect();
		
		source.subscribe(l -> System.out.println("Observer1: " + l));
		
		Thread.sleep(3000);
		
		// Should have emissions replayed for O2 even though items have already been emitted to O1
		source.subscribe(l -> System.out.println("Observer2: " + l));
		
		Thread.sleep(3000);
		
		source.publish().doOnDispose(() -> System.out.println("Shutting down emission stream"));
		
		Thread.sleep(1000);
		
		// No dispose Msg.. Why?
	}
}
