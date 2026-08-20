package net.jrodolfo.java_evolution.java05;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstrates {@code java.util.concurrent}, introduced in Java 5.
 */
public class ConcurrencyUtilitiesExamples {

	/**
	 * Runs a task through an {@link ExecutorService} and obtains the result through
	 * a {@link Future}.
	 *
	 * @param executor executor supplied by the caller
	 * @return task result
	 * @throws Exception if the task fails
	 */
	public String runCallable(ExecutorService executor) throws Exception {
		Callable<String> task = new Callable<String>() {
			@Override
			public String call() {
				return "computed by executor";
			}
		};
		Future<String> future = executor.submit(task);
		return future.get();
	}

	/**
	 * Uses a latch to wait for several tasks to finish and an atomic counter to
	 * count completions safely.
	 *
	 * @param executor executor supplied by the caller
	 * @param taskCount number of tasks to run
	 * @return completed task count
	 * @throws InterruptedException if waiting is interrupted
	 */
	public int countCompletedTasks(ExecutorService executor, int taskCount) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(taskCount);
		AtomicInteger completed = new AtomicInteger();

		for (int index = 0; index < taskCount; index++) {
			executor.submit(new Runnable() {
				@Override
				public void run() {
					completed.incrementAndGet();
					latch.countDown();
				}
			});
		}

		latch.await(2, TimeUnit.SECONDS);
		return completed.get();
	}

	/**
	 * Uses {@link ExecutorService#invokeAll(List)} to run related tasks and obtain
	 * all results.
	 *
	 * @param executor executor supplied by the caller
	 * @return sum of task results
	 * @throws Exception if a task fails
	 */
	public int sumTaskResults(ExecutorService executor) throws Exception {
		java.util.List<Callable<Integer>> tasks = Arrays.asList(
				new Callable<Integer>() {
					@Override
					public Integer call() {
						return 10;
					}
				},
				new Callable<Integer>() {
					@Override
					public Integer call() {
						return 20;
					}
				},
				new Callable<Integer>() {
					@Override
					public Integer call() {
						return 30;
					}
				});
		java.util.List<Future<Integer>> results = executor.invokeAll(tasks);
		int total = 0;

		for (Future<Integer> result : results) {
			total += result.get();
		}

		return total;
	}
}
