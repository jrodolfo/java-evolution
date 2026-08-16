package net.jrodolfo.java_evolution.java24.scoped_values;

/**
 * Explains scoped values fourth preview in Java 24.
 *
 * <p>
 * Scoped values provide immutable contextual data through a bounded dynamic
 * scope. Java 24 continued the preview before the feature became final in Java
 * 25.
 * </p>
 *
 * <p>
 * This repository keeps the Java 24 step as notes because the Java 25 module
 * contains the final runnable learning example.
 * </p>
 */
public class ScopedValuesFourthPreviewNotes {
	/**
	 * Explains the context-propagation problem.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "contextual data such as a user, request ID, or trace ID should reach deeper code without noisy parameters";
	}

	/**
	 * Names the common older approach.
	 *
	 * @return a short explanation
	 */
	public String oldApproach() {
		return "ThreadLocal was commonly used for per-thread context, but mutable values and missing cleanup can leak data";
	}

	/**
	 * Explains the scoped-value model.
	 *
	 * @return a short explanation
	 */
	public String scopedValueModel() {
		return "a scoped value creates an immutable binding that is visible only inside a bounded dynamic scope";
	}

	/**
	 * Explains why Java 24 keeps this entry as a bridge note.
	 *
	 * @return a short explanation
	 */
	public String previewStep() {
		return "Java 24 was the fourth preview, after Java 23 and before finalization in Java 25";
	}

	/**
	 * Describes the feature status across releases.
	 *
	 * @return a short status note
	 */
	public String fourthPreviewStatus() {
		return "scoped values were in fourth preview in Java 24 and final in Java 25";
	}

	/**
	 * Points learners to the final Java 25 module.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 25 scoped_values module for the final runnable learning example";
	}
}
