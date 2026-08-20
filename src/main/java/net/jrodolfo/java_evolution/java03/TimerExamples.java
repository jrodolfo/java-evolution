package net.jrodolfo.java_evolution.java03;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Demonstrates {@link Timer} and {@link TimerTask}.
 */
public class TimerExamples {

	/**
	 * Schedules one delayed task.
	 *
	 * @return number of completed tasks
	 * @throws InterruptedException when waiting is interrupted
	 */
	public int runDelayedTask() throws InterruptedException {
		Timer timer = new Timer("java03-timer", true);
		Completion completion = new Completion();

		try {
			timer.schedule(new TimerTask() {
				public void run() {
					completion.record();
				}
			}, 1L);
			return completion.await(2000L);
		}
		finally {
			timer.cancel();
		}
	}

	static class Completion {
		private int count;
		private boolean done;

		synchronized void record() {
			count++;
			done = true;
			notifyAll();
		}

		synchronized int await(long timeoutMillis) throws InterruptedException {
			long deadline = System.currentTimeMillis() + timeoutMillis;
			while (!done) {
				long remaining = deadline - System.currentTimeMillis();
				if (remaining <= 0L) {
					break;
				}
				wait(remaining);
			}
			return count;
		}
	}
}
