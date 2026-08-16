package net.jrodolfo.java_evolution.java23.scoped_values;

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
	 * Explains the contextual-data problem.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "share immutable contextual data across call chains and child tasks within a bounded scope";
	}

	/**
	 * Compares scoped values with a common older mechanism.
	 *
	 * @return a short comparison
	 */
	public String threadLocalContrast() {
		return "scoped values avoid broad mutable ThreadLocal state and stale-context cleanup hazards";
	}

	/**
	 * Explains the Java 22 connection.
	 *
	 * @return a short explanation
	 */
	public String java22Connection() {
		return "Java 22 continued Scoped Values as a second preview";
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
		return "third preview in Java 23, fourth preview in Java 24, and final in Java 25";
	}
}
