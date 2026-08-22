package net.jrodolfo.java_evolution.java26.structured_concurrency;

/**
 * Explains the sixth preview of structured concurrency in Java 26.
 */
public class StructuredConcurrencySixthPreviewNotes {

	/**
	 * Explains the concurrency problem.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "unstructured concurrent tasks can outlive their caller and make failures, cancellation, and observability harder";
	}

	/**
	 * Explains the structured-concurrency idea.
	 *
	 * @return a short feature explanation
	 */
	public String idea() {
		return "structured concurrency treats related subtasks as one scoped unit of work";
	}

	/**
	 * Describes the Java 26 maturity level.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "structured concurrency is a sixth preview API in Java 26";
	}
}
