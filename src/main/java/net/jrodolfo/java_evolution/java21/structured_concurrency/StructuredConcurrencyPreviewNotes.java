package net.jrodolfo.java_evolution.java21.structured_concurrency;

/**
 * Explains structured concurrency as a Java 21 first preview feature.
 *
 * <p>
 * Concurrent subtasks are easier to understand when they have a clear parent
 * operation. Without that structure, child tasks can outlive the work that
 * started them, and failure handling, cancellation, joining, cleanup, and
 * observability can be spread across unrelated code.
 * </p>
 *
 * <p>
 * Java 21 previewed structured concurrency so related concurrent subtasks could
 * be managed as one structured unit of work. This package documents the
 * first-preview step and points to the later Java 25 module for the fuller
 * preview workflow.
 * </p>
 */
public class StructuredConcurrencyPreviewNotes {

	/**
	 * Explains the practical problem.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "related concurrent subtasks need a clear parent operation and lifetime boundary";
	}

	/**
	 * Explains the common older approach and its risks.
	 *
	 * @return a short explanation
	 */
	public String oldApproachProblem() {
		return "executors, futures, and manual coordination can let child tasks outlive the parent operation";
	}

	/**
	 * Explains the preview mental model.
	 *
	 * @return a short explanation
	 */
	public String previewIdea() {
		return "open a scope, fork related subtasks, join them as a group, handle success or failure, and close the scope";
	}

	/**
	 * Names the coordination benefits.
	 *
	 * @return key coordination benefits
	 */
	public String coordinationBenefits() {
		return "joining, cancellation, failure handling, cleanup, and observability belong to one structured unit of work";
	}

	/**
	 * Describes the Java 21 maturity level.
	 *
	 * @return a short status note
	 */
	public String firstPreviewStatus() {
		return "structured concurrency was first previewed in Java 21";
	}

	/**
	 * Points learners to the later Java 25 module.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 25 structured_concurrency module for the later preview workflow";
	}
}
