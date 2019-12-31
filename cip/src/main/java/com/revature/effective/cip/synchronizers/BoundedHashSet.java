package com.revature.effective.cip.synchronizers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoundedHashSet<T> {
	
	private static final Logger log = LoggerFactory.getLogger(BoundedHashSet.class);

	private final Set<T> set;
	private final Semaphore sem;
	
	// Only 1 constructor
	public BoundedHashSet(int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>());
		this.sem = new Semaphore(bound);
	}
	
	public boolean addNormally(T t) {
		log.info("\nAcquiring... : available - {}, threadsQueued? - {}, queueLength - {}\n", 
				sem.availablePermits(), sem.hasQueuedThreads(), sem.hasQueuedThreads() ? sem.getQueueLength() : 0);
		
		boolean wasAdded = false;
		
		try {
			sem.acquire();
			log.info("\nAcquired Permit : available - {}, threadsQueued? - {}, queueLength - {}\n", 
					sem.availablePermits(), sem.hasQueuedThreads(), sem.hasQueuedThreads() ? sem.getQueueLength() : 0);
			
			wasAdded = set.add(t);
			log.info("\nReturning Normally...\n");
			
		} catch (InterruptedException e) {
			log.error("Interrupted! - ", e);
			log.info("Draining Permits...");
			sem.drainPermits();
			
		} finally {
			if (!wasAdded) {
				log.info("\nReleasing... : available - {}, threadsQueued? - {}, queueLength - {}\n", 
						sem.availablePermits(), sem.hasQueuedThreads(), sem.hasQueuedThreads() ? sem.getQueueLength() : 0);
				sem.release();
				log.info("\nReleased Permit : available - {}, threadsQueued? - {}, queueLength - {}\n", 
						sem.availablePermits(), sem.hasQueuedThreads(), sem.hasQueuedThreads() ? sem.getQueueLength() : 0);
			}
		}
		
		return wasAdded;
	}
	
	public boolean removeNormally(Object o) {
		boolean wasRemoved = set.remove(o);
		if (wasRemoved) {
			log.info("\nReleasing... : available - {}, threadsQueued? - {}, queueLength - {}\n", 
					sem.availablePermits(), sem.hasQueuedThreads(), sem.hasQueuedThreads() ? sem.getQueueLength() : 0);
			sem.release(); // Should this be 1 (for 1 object), or the size of the bound?
			log.info("\nReleased Permit : available - {}, threadsQueued? - {}, queueLength - {}\n", 
					sem.availablePermits(), sem.hasQueuedThreads(), sem.hasQueuedThreads() ? sem.getQueueLength() : 0);
		} else {
			log.info("\nFailed To Remove Normally...\n");
		}
		return wasRemoved;
	}
}
