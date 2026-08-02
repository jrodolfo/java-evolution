package net.jrodolfo.java_evolution.java19;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Demonstrates virtual threads as a Java 19 preview feature.
 *
 * <p>
 * Virtual threads became final later, in Java 21. This project compiles on JDK
 * 25, so the example uses the final API while documenting the Java 19 preview
 * origin.
 * </p>
 */
public class VirtualThreadsPreviewExamples {

	/**
	 * Starts a virtual thread and returns its result.
	 *
	 * @return text produced inside a virtual thread
	 * @throws InterruptedException when waiting is interrupted
	 * @throws ExecutionException when the task fails
	 */
	public String runInVirtualThread() throws InterruptedException, ExecutionException {
		FutureTask<String> task = new FutureTask<>(() -> Thread.currentThread().isVirtual()
				? "running in virtual thread"
				: "running in platform thread");
		Thread thread = Thread.ofVirtual().name("java19-example").start(task);
		thread.join();
		return task.get();
	}

	/**
	 * Creates a virtual thread without starting it immediately.
	 *
	 * @return whether the created thread is virtual
	 */
	public boolean unstartedThreadIsVirtual() {
		Thread thread = Thread.ofVirtual().unstarted(() -> {
		});
		return thread.isVirtual();
	}
}
