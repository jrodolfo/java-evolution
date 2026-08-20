package net.jrodolfo.java_evolution.java01;

/**
 * Refreshes early Java {@link Thread} and {@link Runnable} usage.
 */
public class ThreadBasicsExamples {

	/**
	 * Runs work in a thread created with a {@link Runnable}.
	 *
	 * @return the value changed by the worker thread
	 * @throws InterruptedException when waiting is interrupted
	 */
	public int runRunnableInThread() throws InterruptedException {
		final int[] value = new int[1];
		Thread worker = new Thread(new Runnable() {
			public void run() {
				value[0] = 42;
			}
		});
		worker.start();
		worker.join();
		return value[0];
	}

	/**
	 * Uses synchronization to protect a shared counter.
	 *
	 * @return the final counter value
	 */
	public int synchronizedCounter() {
		Counter counter = new Counter();
		counter.increment();
		counter.increment();
		return counter.value();
	}

	static class Counter {
		private int value;

		synchronized void increment() {
			value++;
		}

		synchronized int value() {
			return value;
		}
	}
}
