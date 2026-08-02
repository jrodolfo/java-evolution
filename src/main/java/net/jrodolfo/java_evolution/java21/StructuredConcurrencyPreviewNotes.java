package net.jrodolfo.java_evolution.java21;

/**
 * Explains structured concurrency as a Java 21 preview feature.
 *
 * <p>
 * Concurrent subtasks are easier to understand when they have a clear parent
 * scope. Structured concurrency groups related tasks so joining, cancellation,
 * failure handling, and observability belong to one unit of work.
 * </p>
 *
 * <p>
 * The Java 21 API was preview, so this repository documents the concept without
 * depending on preview APIs.
 * </p>
 */
public class StructuredConcurrencyPreviewNotes {

	/**
	 * Explains the central idea.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "structured concurrency groups related concurrent tasks into a single unit";
	}

	/**
	 * Describes the Java 21 maturity level.
	 *
	 * @return a short status note
	 */
	public String previewStatus() {
		return "structured concurrency was preview in Java 21";
	}
}
