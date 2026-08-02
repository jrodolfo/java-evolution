package net.jrodolfo.java_evolution.java20;

/**
 * Explains structured concurrency as a Java 20 second incubator feature.
 *
 * <p>
 * When a parent operation starts several concurrent subtasks, the subtasks
 * should have a clear lifetime relationship with the parent. Structured
 * concurrency tries to make joining, cancellation, failure handling, and
 * observability part of one coordinated unit of work.
 * </p>
 *
 * <p>
 * The Java 20 API was still incubating, so the repository keeps this entry as
 * notes instead of depending on an incubator module.
 * </p>
 */
public class StructuredConcurrencySecondIncubatorNotes {

	/**
	 * Explains the central idea.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "structured concurrency treats related concurrent subtasks as one unit of work";
	}

	/**
	 * Names the practical benefit for concurrent code.
	 *
	 * @return a short explanation
	 */
	public String benefit() {
		return "it improves cancellation, failure handling, and observability for concurrent code";
	}

	/**
	 * Explains why this project uses notes.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the Java 20 API was incubating, so the example stays as notes";
	}
}
