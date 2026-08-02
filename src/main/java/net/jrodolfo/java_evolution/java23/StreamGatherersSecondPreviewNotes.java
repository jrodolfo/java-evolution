package net.jrodolfo.java_evolution.java23;

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
	public String purpose() {
		return "define custom intermediate stream operations";
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
