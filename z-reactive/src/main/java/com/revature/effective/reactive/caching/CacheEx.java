package com.revature.effective.reactive.caching;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class CacheEx {
	public static void main (String[] args) throws InterruptedException {
		Observable<Integer> source = Observable.interval(1,TimeUnit.SECONDS).map(i -> 
			i.intValue()).scan(0, (total, n) -> 
				total += n).cache();
		
		source.subscribe(i -> System.out.println("Obs1: " + i));
		
		Thread.sleep(2000);
		
		source.subscribe(i -> System.out.println("Obs2: " + i));
		
		Thread.sleep(2000);
	
		source.subscribe(i -> System.out.println("Obs3: " + i));
		
		Thread.sleep(2000);
	
		source.subscribe(i -> System.out.println("Obs4: " + i));
		
		// This is essentially a long-term replay().autoConnect() - we know that we do not want to dispose() any time soon.
	}
}
