package net.jrodolfo.java_evolution.java19;

/**
 * Explains structured concurrency as an incubating feature in Java 19.
 *
 * <p>
 * The structured concurrency API evolved after Java 19, so this repository uses
 * notes instead of depending on an incubator module or a specific preview API
 * shape.
 * </p>
 */
public class StructuredConcurrencyPreviewNotes {

	/**
	 * Explains the main idea.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "treat related concurrent tasks as one structured unit of work";
	}

	/**
	 * Names the kind of problem it helps with.
	 *
	 * @return a short explanation
	 */
	public String benefit() {
		return "make cancellation, failure handling, and joining easier to reason about";
	}

	/**
	 * Explains why this repository keeps it as notes.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the Java 19 API was incubating, so the repository documents the concept without enabling incubator modules";
	}
}
