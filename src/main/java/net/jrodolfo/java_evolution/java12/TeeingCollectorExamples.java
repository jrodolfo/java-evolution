package net.jrodolfo.java_evolution.java12;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Demonstrates {@link Collectors#teeing(java.util.stream.Collector,
 * java.util.stream.Collector, java.util.function.BiFunction)}, introduced in
 * Java 12.
 */
public class TeeingCollectorExamples {

	/**
	 * Computes minimum and maximum values in one stream pass.
	 *
	 * @param numbers the numbers to summarize
	 * @return the range between the minimum and maximum values
	 */
	public int range(List<Integer> numbers) {
		return numbers.stream()
				.collect(Collectors.teeing(
						Collectors.minBy(Integer::compareTo),
						Collectors.maxBy(Integer::compareTo),
						(minimum, maximum) -> maximum.orElse(0) - minimum.orElse(0)));
	}

	/**
	 * Computes a count and average in one stream pass.
	 *
	 * @param numbers the numbers to summarize
	 * @return a simple summary object
	 */
	public NumberSummary summarize(List<Integer> numbers) {
		return numbers.stream()
				.collect(Collectors.teeing(
						Collectors.counting(),
						Collectors.summarizingDouble(Integer::doubleValue),
						(count, statistics) -> new NumberSummary(count, statistics)));
	}

	/**
	 * Small Java 12-style data class for teeing collector results.
	 */
	public static class NumberSummary {
		private final long count;
		private final DoubleSummaryStatistics statistics;

		public NumberSummary(long count, DoubleSummaryStatistics statistics) {
			this.count = count;
			this.statistics = statistics;
		}

		/**
		 * @return how many numbers were processed
		 */
		public long count() {
			return count;
		}

		/**
		 * @return the average value
		 */
		public double average() {
			return statistics.getAverage();
		}
	}
}
