//package com.revature.effective.cip.synchronization;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.revature.effective.cip.annotation.GuardedBy;
//import com.revature.effective.cip.annotation.ThreadSafe;
//
///** But should we use client-side locking?
// * Never, if it can be helped. Client-side locking violates encapsulation of synchronization policy.
// * It is not a good idea to disperse synchroinzation implementation amongst source files, especially
// * when those source files are not maintained by the same source.
// */
//
///** Note, in this example we do not have a constructor - list is public. We can easily redesign 
// * ExtendedList to guard the lock of list by making a constructor and assigning an external list
// * to this.list 
// */
//
//@ThreadSafe
//public class SafeExtendedList<T> {
//	
//	@GuardedBy(lock = "this")
//	private List<T> list = new ArrayList<>();
//	
//	/* Now, list is guarded by the intrinsic lock of SafeExtendedList */
//	public SafeExtendedList(List<T> list) {
//		this.list = list;
//	}
//	
//	public synchronized boolean badPutIfAbsent(T t) {
//		boolean absent = !list.contains(t);
//		if (absent) {
//			list.add(t);
//		}
//		return absent;
//	}
//}