package net.jrodolfo.java_evolution.java20.scoped_values;

/**
 * Explains scoped values as a Java 20 incubator feature.
 *
 * <p>
 * Java 20 introduced scoped values as an incubating API. The feature explored a
 * safer way to make immutable contextual data available to code deeper in a
 * call chain, without forcing every intermediate method to receive and forward
 * that value as a parameter.
 * </p>
 *
 * <p>
 * This package keeps the Java 20 step explanatory because the incubator API
 * required an incubator module and changed before the feature became final.
 * </p>
 */
public class ScopedValuesIncubatorNotes {

	/**
	 * Explains the practical problem.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "contextual data such as request IDs often needs to reach deep code without noisy parameter plumbing";
	}

	/**
	 * Compares scoped values with a common older approach.
	 *
	 * @return a short comparison
	 */
	public String threadLocalProblem() {
		return "ThreadLocal can store context on a thread, but mutable thread-associated state is easy to leak or forget to clean up";
	}

	/**
	 * Describes what Java 20 introduced.
	 *
	 * @return a short explanation
	 */
	public String incubatorIdea() {
		return "scoped values explored immutable contextual data with a bounded lifetime inside a scoped operation";
	}

	/**
	 * Describes the Java 20 maturity level.
	 *
	 * @return a short status note
	 */
	public String incubatorStatus() {
		return "scoped values were introduced in Java 20 as an incubating API";
	}

	/**
	 * Explains why this package keeps Java 20 scoped values explanatory.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the Java 20 API required an incubator module, so this repository documents the concept without enabling old incubator APIs";
	}

	/**
	 * Points learners to the later final API example.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 25 scoped_values module for the final runnable ScopedValue example";
	}
}
