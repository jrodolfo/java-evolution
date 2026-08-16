package net.jrodolfo.java_evolution.java24.structured_concurrency;

/**
 * Explains structured concurrency fourth preview in Java 24.
 *
 * <p>
 * Structured concurrency keeps related concurrent subtasks inside a clear
 * parent scope. Java 24 continued the preview so the API could keep evolving
 * before finalization.
 * </p>
 *
 * <p>
 * This repository keeps the Java 24 step as notes because the API was still a
 * preview feature. The later Java 25 module has the fuller learning material.
 * </p>
 */
public class StructuredConcurrencyFourthPreviewNotes {
	/**
	 * Explains the practical problem.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "related concurrent subtasks need one parent scope for joining, cancellation, and failure handling";
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
	 * Explains why Java 24 keeps this entry as a bridge note.
	 *
	 * @return a short explanation
	 */
	public String previewStep() {
		return "Java 24 was the fourth preview, after Java 23 and before the Java 25 fifth preview";
	}

	/**
	 * Points learners to the next preview module.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 25 structured_concurrency module because this feature is still preview";
	}

	/**
	 * Describes the Java 24 maturity level.
	 *
	 * @return a short status note
	 */
	public String fourthPreviewStatus() {
		return "structured concurrency was in fourth preview in Java 24";
	}
}
