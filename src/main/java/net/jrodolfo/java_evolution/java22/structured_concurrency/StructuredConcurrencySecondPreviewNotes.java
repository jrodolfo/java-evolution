package net.jrodolfo.java_evolution.java22.structured_concurrency;

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
	 * Explains the practical problem.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "related concurrent subtasks need clear parent, lifetime, and cancellation boundaries";
	}

	/**
	 * Explains the structured unit of work.
	 *
	 * @return a short explanation
	 */
	public String structuredUnitOfWork() {
		return "a parent operation opens a scope, forks related subtasks, joins them, and closes the scope";
	}

	/**
	 * Names important behavior that structure makes clearer.
	 *
	 * @return key behavior
	 */
	public String coordinationBenefits() {
		return "joining, failure handling, cancellation, cleanup, and observability become easier to reason about";
	}

	/**
	 * Points learners to the later Java 25 preview module.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 25 structured_concurrency module because this feature is still preview";
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
