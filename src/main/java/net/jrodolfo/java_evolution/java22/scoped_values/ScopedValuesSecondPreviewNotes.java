package net.jrodolfo.java_evolution.java22.scoped_values;

/**
 * Explains scoped values as a Java 22 second preview feature.
 *
 * <p>
 * Scoped values address the same problem as the Java 21 preview: sharing
 * immutable contextual data through a bounded scope without the cleanup hazards
 * of many {@link ThreadLocal} designs.
 * </p>
 *
 * <p>
 * Java 22 kept the API in preview, so this repository keeps the version entry
 * explanatory and points to the final Java 25 scoped-values module for runnable
 * code.
 * </p>
 */
public class ScopedValuesSecondPreviewNotes {

	/**
	 * Explains the goal of scoped values.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "share immutable contextual data across a call chain without passing it through every method";
	}

	/**
	 * Compares scoped values with a common older approach.
	 *
	 * @return a short comparison
	 */
	public String threadLocalMotivation() {
		return "scoped values avoid many ThreadLocal cleanup and stale-context hazards";
	}

	/**
	 * Explains bounded dynamic scope in plain language.
	 *
	 * @return a short explanation
	 */
	public String boundedDynamicScope() {
		return "a binding is visible while the scoped operation runs and disappears when that operation finishes";
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
	 * Describes the Java 22 maturity level.
	 *
	 * @return a short status note
	 */
	public String secondPreviewStatus() {
		return "scoped values were in second preview in Java 22";
	}
}
