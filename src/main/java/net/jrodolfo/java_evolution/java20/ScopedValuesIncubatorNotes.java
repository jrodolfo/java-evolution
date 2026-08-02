package net.jrodolfo.java_evolution.java20;

/**
 * Explains scoped values, introduced as an incubating API in Java 20.
 *
 * <p>
 * {@link ThreadLocal} can pass contextual data through a call stack without
 * adding parameters everywhere, but mutable thread-local state is easy to leak
 * or forget to clean up. Scoped values were introduced to provide a safer model
 * for sharing immutable context within a bounded execution scope.
 * </p>
 *
 * <p>
 * The Java 20 API was incubating, so this repository documents the problem and
 * intent without depending on the incubator module.
 * </p>
 */
public class ScopedValuesIncubatorNotes {

	/**
	 * Explains the basic goal of scoped values.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "scoped values share immutable data within and across threads";
	}

	/**
	 * Compares scoped values with a common older approach.
	 *
	 * @return a short comparison
	 */
	public String relationToThreadLocal() {
		return "scoped values are intended as a safer alternative to many ThreadLocal use cases";
	}

	/**
	 * Explains why this package keeps Java 20 scoped values as notes.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the Java 20 API was incubating, so this repository documents the concept without enabling incubator modules";
	}
}
