package com.revature.effective.nintotwelve.multithreading;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RunnableRefresher implements Runnable {

	private Thread thread = Thread.currentThread();
	private Runtime runtime = Runtime.getRuntime();
	
	public void run() {

		this.thread.setPriority(5);
		if (this.thread.getId() % 2 == 0) this.thread.setDaemon(true);

		System.out.println("Free Memory: " + this.runtime.freeMemory()/Math.pow(1000,3.0) + " GB");
		System.out.println("Max Memory: " + this.runtime.maxMemory()/Math.pow(1000,3.0) + " GB");
		System.out.println("Total Memory: " + this.runtime.totalMemory()/Math.pow(1000,3.0) + " GB");
		
		System.out.println("Name - " + this.thread.getName());
		System.out.println("Id - " +  this.thread.getId());
		System.out.println("ContextClasLoader - " + this.thread.getContextClassLoader());
		System.out.println("Alive? - " + this.thread.isAlive());
		System.out.println("ToString - " + this.thread.toString());
		System.out.println("State - " + this.thread.getState());
		System.out.println("Daemon - " + this.thread.isDaemon());
		System.out.println("Thread Group - " + this.thread.getThreadGroup());
	}

	/** The stack size
    * is the approximate number of bytes of address space that the virtual
    * machine is to allocate for this thread's stack.  <b>The effect of the
    * {@code stackSize} parameter, if any, is highly platform dependent.</b>
    * 
    * Thread stack size 
    * is the amount of memory allocated to a single Java Virtual Machine (JVM) thread. 
    * The ZCS application can specify JVM thread stack size using zmlocalconfig. 
	* If you are running ZCS 4.5.10 or 5.0, the default thread stack size is 256k.
	* 
	* This basically determines the stack size for each thread - how many local
	* variables can be encapsulated in a single thread before a stack overflow 
	* exception is thrown.
	* 
	* The stack size is determined when the thread is created since it needs to 
	* occupy contiguous address space. 
	* That means that the entire address space for the thread's stack has to 
	* be reserved at the point of creating the thread. 
	* 
	* Once a thread is started, it has already been created and is now in the runnable
	* state. From this point on, an IllegalStateException would be thrown if there
	* is an attempt to start the thread again (more than once).
	* 
	* After run() is called on the thread that has been started, it is either in the 
	* running, waiting, or dead state. After it has died, it must be recreated in order
	* to be restarted.
	* 
	* Once the thread has been started, it is entirely up to the Scheduler to run the 
	* thread, giving it its running status - otherwise it will wait for the Scheduler
	* to run it (in waiting state)
	* 
	* yield() will cause the thread to return to the waiting state as soon as possible,
	* and re-enter its running state as soon as other (presumably higher priority) threads
	* have finished running.
	* 
	* Keep reading source code - left off on #stop()
    */
}
