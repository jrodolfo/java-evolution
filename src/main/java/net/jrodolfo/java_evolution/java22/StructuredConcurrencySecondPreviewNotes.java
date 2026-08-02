package net.jrodolfo.java_evolution.java22;

/**
 * Explains structured concurrency as a Java 22 second preview feature.
 *
 * <p>
 * Structured concurrency keeps related concurrent subtasks inside a clear
 * parent scope. That makes cancellation, joining, failure handling, and
 * observability easier to reason about than detached background tasks.
 * </p>
 *
 * <p>
 * Java 22 kept the API in preview, so this repository documents the concept
 * without depending on a preview API shape.
 * </p>
 */
public class StructuredConcurrencySecondPreviewNotes {

	/**
	 * Explains the practical goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "manage related concurrent subtasks with clear lifetime and cancellation boundaries";
	}

	/**
	 * Describes the Java 22 maturity level.
	 *
	 * @return a short status note
	 */
	public String secondPreviewStatus() {
		return "structured concurrency was in second preview in Java 22";
	}
}
