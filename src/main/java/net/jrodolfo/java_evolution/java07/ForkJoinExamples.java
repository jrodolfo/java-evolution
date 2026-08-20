package net.jrodolfo.java_evolution.java07;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Demonstrates the fork/join framework introduced in Java 7.
 */
public class ForkJoinExamples {

	/**
	 * Sums numbers by recursively splitting the range into subtasks.
	 *
	 * @param values values to sum
	 * @return total sum
	 */
	public int parallelSum(int[] values) {
		ForkJoinPool pool = new ForkJoinPool();
		return pool.invoke(new SumTask(values, 0, values.length));
	}

	static class SumTask extends RecursiveTask<Integer> {
		private static final int THRESHOLD = 4;

		private final int[] values;
		private final int startInclusive;
		private final int endExclusive;

		SumTask(int[] values, int startInclusive, int endExclusive) {
			this.values = values;
			this.startInclusive = startInclusive;
			this.endExclusive = endExclusive;
		}

		@Override
		protected Integer compute() {
			int length = endExclusive - startInclusive;

			if (length <= THRESHOLD) {
				int sum = 0;
				for (int index = startInclusive; index < endExclusive; index++) {
					sum += values[index];
				}
				return sum;
			}

			int midpoint = startInclusive + length / 2;
			SumTask left = new SumTask(values, startInclusive, midpoint);
			SumTask right = new SumTask(values, midpoint, endExclusive);

			left.fork();
			int rightResult = right.compute();
			int leftResult = left.join();
			return leftResult + rightResult;
		}
	}
}
