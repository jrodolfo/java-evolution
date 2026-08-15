package net.jrodolfo.java_evolution.java25.structured_concurrency;

/**
 * Explains Structured Concurrency, previewed for the fifth time in Java 25 by
 * JEP 505.
 *
 * <p>
 * This is an explanatory learning module because the Java 25
 * {@code StructuredTaskScope} API is still preview. A faithful executable
 * example would require enabling preview features for the Maven build.
 * </p>
 */
public class StructuredConcurrencyFifthPreviewNotes {

	/**
	 * Explains the problem that structured concurrency addresses.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "related concurrent subtasks can otherwise be started, joined, cancelled, and observed in scattered places";
	}

	/**
	 * Explains how this problem is commonly handled without structured
	 * concurrency.
	 *
	 * @return a short before-Java-25 explanation
	 */
	public String commonAlternative() {
		return "developers often coordinate related work with ExecutorService, Future objects, manual cancellation, and try-finally cleanup";
	}

	/**
	 * Explains the Java 25 structured-concurrency idea.
	 *
	 * @return a short feature explanation
	 */
	public String java25Idea() {
		return "StructuredTaskScope treats related subtasks as one structured unit of work with clear fork, join, failure, cancellation, and close points";
	}

	/**
	 * Describes the Java 25 maturity level.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "structured concurrency is in fifth preview in Java 25 and requires --enable-preview";
	}
}
