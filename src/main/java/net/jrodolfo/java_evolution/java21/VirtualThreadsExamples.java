package net.jrodolfo.java_evolution.java21;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstrates virtual threads, finalized in Java 21.
 *
 * <p>
 * Traditional platform threads are easy to program with, but each one is a
 * relatively expensive operating-system resource. That makes simple
 * thread-per-task code hard to scale for workloads with many blocking I/O
 * operations.
 * </p>
 *
 * <p>
 * Virtual threads solve that scalability problem while preserving the familiar
 * blocking style. They are especially useful for request handling and network
 * I/O where tasks spend much of their time waiting.
 * </p>
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
		Thread.Builder.OfVirtual builder = Thread.ofVirtual().name("worker-", 1);

		for (int index = 1; index <= 3; index++) {
			Thread thread = builder.start(() -> names.add(Thread.currentThread().getName()));
			threads.add(thread);
		}

		for (Thread thread : threads) {
			thread.join();
		}
		return names;
	}

	/**
	 * Starts many virtual threads that briefly block, then waits until all of them
	 * complete.
	 *
	 * <p>
	 * This is not a benchmark. It is a small demonstration of the design goal:
	 * virtual threads make thread-per-task code practical for many tasks that
	 * spend most of their time waiting.
	 * </p>
	 *
	 * @param numberOfTasks number of virtual threads to start
	 * @return number of completed tasks
	 * @throws InterruptedException when waiting is interrupted
	 */
	public int runManyBlockingVirtualThreadTasks(int numberOfTasks) throws InterruptedException {
		CountDownLatch started = new CountDownLatch(numberOfTasks);
		CountDownLatch release = new CountDownLatch(1);
		AtomicInteger completed = new AtomicInteger();
		List<Thread> threads = new ArrayList<>();

		for (int index = 0; index < numberOfTasks; index++) {
			Thread thread = Thread.ofVirtual().start(() -> {
				started.countDown();
				try {
					release.await();
					Thread.sleep(1);
					completed.incrementAndGet();
				} catch (InterruptedException exception) {
					Thread.currentThread().interrupt();
				}
			});
			threads.add(thread);
		}

		started.await();
		release.countDown();

		for (Thread thread : threads) {
			thread.join();
		}

		return completed.get();
	}
}
