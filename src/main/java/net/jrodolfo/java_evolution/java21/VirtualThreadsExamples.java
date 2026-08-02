package net.jrodolfo.java_evolution.java21;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Demonstrates virtual threads, finalized in Java 21.
 */
public class VirtualThreadsExamples {

	/**
	 * Runs one task in a virtual thread.
	 *
	 * @return whether the task observed a virtual thread
	 * @throws InterruptedException when waiting is interrupted
	 * @throws ExecutionException when the task fails
	 */
	public boolean taskRunsInVirtualThread() throws InterruptedException, ExecutionException {
		FutureTask<Boolean> task = new FutureTask<>(() -> Thread.currentThread().isVirtual());
		Thread thread = Thread.ofVirtual().name("java21-virtual-thread").start(task);
		thread.join();
		return task.get();
	}

	/**
	 * Starts several virtual threads and collects their names.
	 *
	 * @return virtual thread names
	 * @throws InterruptedException when waiting is interrupted
	 */
	public List<String> startNamedVirtualThreads() throws InterruptedException {
		List<String> names = java.util.Collections.synchronizedList(new ArrayList<>());
		List<Thread> threads = new ArrayList<>();

		for (int index = 1; index <= 3; index++) {
			Thread thread = Thread.ofVirtual().name("worker-", 1).start(() -> names.add(Thread.currentThread().getName()));
			threads.add(thread);
		}

		for (Thread thread : threads) {
			thread.join();
		}
		return names;
	}
}
