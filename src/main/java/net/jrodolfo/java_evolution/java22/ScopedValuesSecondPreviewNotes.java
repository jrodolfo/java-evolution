package net.jrodolfo.java_evolution.java22;

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
 * as notes.
 * </p>
 */
public class ScopedValuesSecondPreviewNotes {

	/**
	 * Explains the goal of scoped values.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "share immutable values safely across a bounded dynamic scope";
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
