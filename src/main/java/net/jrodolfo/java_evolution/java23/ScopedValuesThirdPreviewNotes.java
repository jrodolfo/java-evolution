package net.jrodolfo.java_evolution.java23;

/**
 * Explains scoped values as a Java 23 third preview feature.
 *
 * <p>
 * Scoped values address the common need to pass immutable contextual data
 * through a bounded execution scope. They are easier to reason about than broad
 * mutable thread-local state, especially with many threads or child tasks.
 * </p>
 */
public class ScopedValuesThirdPreviewNotes {

	/**
	 * Explains the feature goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "share immutable data across call chains and child threads within a bounded scope";
	}

	/**
	 * Points learners to the final Java 25 module.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 25 scoped_values module for the final runnable learning example";
	}

	/**
	 * Describes the feature status across releases.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "third preview in Java 23 and final in Java 25";
	}
}
