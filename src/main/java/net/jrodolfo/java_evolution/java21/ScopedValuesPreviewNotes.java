package net.jrodolfo.java_evolution.java21;

/**
 * Explains scoped values as a Java 21 preview feature.
 *
 * <p>
 * Contextual data such as request IDs or security context is often passed with
 * {@link ThreadLocal}, but thread-local state can be mutable and difficult to
 * clean up correctly. Scoped values provide immutable context bound to a limited
 * execution scope.
 * </p>
 *
 * <p>
 * The Java 21 API was preview, so this repository keeps the feature as notes.
 * </p>
 */
public class ScopedValuesPreviewNotes {

	/**
	 * Explains the goal of scoped values.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "scoped values share immutable data across call chains and child threads";
	}

	/**
	 * Describes the Java 21 maturity level.
	 *
	 * @return a short status note
	 */
	public String previewStatus() {
		return "scoped values were preview in Java 21";
	}
}
