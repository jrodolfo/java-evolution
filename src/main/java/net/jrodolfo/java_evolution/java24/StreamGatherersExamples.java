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
 *
 * <p>
 * A gatherer is useful when each emitted output may depend on more than one
 * input element, or on state accumulated while the stream is flowing. That is
 * why windowing and scanning are good teaching examples.
 * </p>
 */
public class StreamGatherersExamples {

	/**
	 * Groups values into fixed-size windows. Windowing is an intermediate
	 * operation because it changes how elements flow through the pipeline before
	 * the final {@code toList()} terminal operation runs.
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
	 * Produces a running sum. Scanning is stateful: each output depends on the
	 * accumulated state from earlier input values.
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
