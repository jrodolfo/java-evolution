package net.jrodolfo.java_evolution.java23;

/**
 * Explains structured concurrency as a Java 23 third preview feature.
 *
 * <p>
 * When related subtasks are started independently, cancellation, failure
 * handling, and observation become scattered. Structured concurrency groups
 * related subtasks into one parent operation so their lifetime is easier to
 * understand.
 * </p>
 */
public class StructuredConcurrencyThirdPreviewNotes {

	/**
	 * Explains the concurrency model.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "treat related concurrent subtasks as one observable unit of work";
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
	 * Describes the feature status across releases.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "third preview in Java 23 and fifth preview in Java 25";
	}
}
