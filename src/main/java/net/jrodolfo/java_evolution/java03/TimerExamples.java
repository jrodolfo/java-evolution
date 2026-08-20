package net.jrodolfo.java_evolution.java03;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
		CountDownLatch completed = new CountDownLatch(1);
		AtomicInteger count = new AtomicInteger();

		try {
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					count.incrementAndGet();
					completed.countDown();
				}
			}, 1L);
			completed.await(2, TimeUnit.SECONDS);
			return count.get();
		}
		finally {
			timer.cancel();
		}
	}
}
