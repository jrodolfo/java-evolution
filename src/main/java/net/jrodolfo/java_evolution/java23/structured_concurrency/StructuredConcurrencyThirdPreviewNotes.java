package net.jrodolfo.java_evolution.java23.structured_concurrency;

/**
 * Explains structured concurrency as a Java 23 third preview feature.
 *
 * <p>
 * Structured concurrency keeps related concurrent subtasks inside a clear
 * parent scope. Java 23 continued the preview so developers could keep testing
 * the API before finalization.
 * </p>
 *
 * <p>
 * This repository keeps the Java 23 step as notes because the API was still a
 * preview feature. The later Java 25 module has the fuller learning material.
 * </p>
 */
public class StructuredConcurrencyThirdPreviewNotes {

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
	 * Explains why Java 23 keeps this entry as a bridge note.
	 *
	 * @return a short explanation
	 */
	public String previewStep() {
		return "Java 23 was the third preview, between the Java 22 second preview and later preview rounds";
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
	 * Describes the Java 23 maturity level.
	 *
	 * @return a short status note
	 */
	public String thirdPreviewStatus() {
		return "structured concurrency was in third preview in Java 23";
	}
}
