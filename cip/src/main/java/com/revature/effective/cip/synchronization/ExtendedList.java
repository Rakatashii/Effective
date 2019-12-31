package com.revature.effective.cip.synchronization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.revature.effective.cip.annotation.GuardedBy;
import com.revature.effective.cip.annotation.ThreadSafeMethod;
import com.revature.effective.cip.annotation.UnsafeMethod;

/* Client-side locking:
 * entails guarding client code that uses some object X with the lock X uses to guard its own
 * state. In order to use client-side locking, you must know what lock X uses.
 */
public class ExtendedList<E> {
	
	@GuardedBy(lock = "list.this in ArrayList")
	private List<E> list = Collections.synchronizedList(new ArrayList<E>());
	
	/* Bad - Yes the method is synchronized, but it is synchronized on the wrong lock. All of the
	 * methods in List/ArrayList are synchronized on the locks of those respective classes. Meaning,
	 * if we do one operation with the ExtendedList, there is no guarantee that modifications will
	 * not conflict with a simultaneous modifying List/ArrayList operation. 
	 */
	
	@UnsafeMethod
	public synchronized boolean badPutIfAbsent(E e) {
		boolean absent = !list.contains(e);
		if (absent) {
			list.add(e);
		}
		return absent;
	}
	
	/* Not bad: */
	@ThreadSafeMethod
	public boolean putIfAbsent(E e) {
		synchronized(list) {
			boolean absent = !list.contains(e);
			if (absent) {
				list.add(e);
			}
			return absent;
		}
	}
}


