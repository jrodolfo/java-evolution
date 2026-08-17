package net.jrodolfo.java_evolution.java21.scoped_values;

/**
 * Explains scoped values as a Java 21 first preview feature.
 *
 * <p>
 * Contextual data such as request IDs, tenant IDs, trace IDs, or security
 * context is often needed several calls below the method that first received
 * it. Passing that value through every method can make signatures noisy, while
 * {@link ThreadLocal} can introduce mutable state, cleanup burden, and stale
 * context risks.
 * </p>
 *
 * <p>
 * Java 21 previewed scoped values as a safer model for immutable contextual
 * data with a bounded lifetime. This package documents the first-preview step
 * and points to the Java 25 module for final runnable code.
 * </p>
 */
public class ScopedValuesPreviewNotes {

	/**
	 * Explains the contextual-data problem.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "contextual data such as request IDs should be available across a call chain without passing it through every method";
	}

	/**
	 * Explains the common older approach and its risks.
	 *
	 * @return a short explanation
	 */
	public String threadLocalProblem() {
		return "ThreadLocal can carry mutable thread-associated state that must be cleaned up to avoid stale context";
	}

	/**
	 * Explains the preview idea.
	 *
	 * @return a short explanation
	 */
	public String previewIdea() {
		return "scoped values bind immutable contextual data for a bounded dynamic scope";
	}

	/**
	 * Describes the Java 21 maturity level.
	 *
	 * @return a short status note
	 */
	public String firstPreviewStatus() {
		return "scoped values were first previewed in Java 21";
	}

	/**
	 * Points learners to the final Java 25 module.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 25 scoped_values module for the final runnable Scoped Values example";
	}
}
