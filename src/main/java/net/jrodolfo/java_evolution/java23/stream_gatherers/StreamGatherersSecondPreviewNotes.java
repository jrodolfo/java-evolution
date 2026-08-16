package net.jrodolfo.java_evolution.java23.stream_gatherers;

/**
 * Explains Stream Gatherers as a Java 23 second preview feature.
 *
 * <p>
 * Streams had many built-in intermediate operations, but custom intermediate
 * operations such as windowing, scanning, and batching were hard to express.
 * Gatherers provide an extension point for those transformations.
 * </p>
 */
public class StreamGatherersSecondPreviewNotes {

	/**
	 * Explains the feature goal.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "custom intermediate stream operations such as windowing, scanning, and batching were hard to express";
	}

	/**
	 * Explains the Java 22 connection.
	 *
	 * @return a short explanation
	 */
	public String java22Connection() {
		return "Java 22 introduced Stream Gatherers as a first preview";
	}

	/**
	 * Explains the Java 23 status.
	 *
	 * @return a short explanation
	 */
	public String java23Status() {
		return "Java 23 continued Stream Gatherers as a second preview";
	}

	/**
	 * Points learners to the final Java 24 example.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read StreamGatherersExamples in Java 24 for the final runnable gatherers example";
	}

	/**
	 * Describes the feature status across releases.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "second preview in Java 23 and final in Java 24";
	}
}
