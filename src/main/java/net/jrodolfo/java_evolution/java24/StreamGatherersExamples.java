package net.jrodolfo.java_evolution.java24;

import java.util.List;
import java.util.stream.Gatherers;

/**
 * Demonstrates Stream Gatherers, finalized in Java 24.
 *
 * <p>
 * Before gatherers, custom intermediate stream operations were difficult to
 * express. Developers often had to leave the stream pipeline or force the
 * problem into a terminal collector. Gatherers let streams model operations
 * such as fixed windows and running scans as part of the pipeline.
 * </p>
 */
public class StreamGatherersExamples {

	/**
	 * Groups values into fixed-size windows.
	 *
	 * @param values values to window
	 * @param size window size
	 * @return fixed-size windows, with a smaller final window when needed
	 */
	public List<List<Integer>> fixedWindows(List<Integer> values, int size) {
		return values.stream()
				.gather(Gatherers.windowFixed(size))
				.toList();
	}

	/**
	 * Produces a running sum.
	 *
	 * @param values values to scan
	 * @return running sum after each value
	 */
	public List<Integer> runningSum(List<Integer> values) {
		return values.stream()
				.gather(Gatherers.scan(() -> 0, Integer::sum))
				.toList();
	}
}
