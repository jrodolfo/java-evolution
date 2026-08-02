package net.jrodolfo.java_evolution.java22;

/**
 * Explains Stream Gatherers, introduced as a Java 22 preview feature.
 *
 * <p>
 * Streams provide many built-in intermediate operations, but some useful
 * transformations, such as windowing, scanning, or batching, historically did
 * not fit cleanly into the stream pipeline. Gatherers were introduced to make
 * custom intermediate operations expressible.
 * </p>
 *
 * <p>
 * The Java 22 API was preview, so this repository documents the intent and
 * keeps the runnable final example in the later Java 24 package.
 * </p>
 */
public class StreamGatherersPreviewNotes {

	/**
	 * Explains the central idea.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "stream gatherers allow custom intermediate stream operations";
	}

	/**
	 * Gives common use cases.
	 *
	 * @return example transformations
	 */
	public String exampleUseCase() {
		return "windowing, scanning, and other transformations not covered by built-in stream operations";
	}

	/**
	 * Explains why this Java 22 package keeps the feature as notes.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the Java 22 API was preview, so this repository keeps the entry as notes";
	}
}
