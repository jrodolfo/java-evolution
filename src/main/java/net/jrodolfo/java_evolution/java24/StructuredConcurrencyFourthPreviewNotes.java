package net.jrodolfo.java_evolution.java24;

/**
 * Explains structured concurrency fourth preview in Java 24.
 *
 * <p>
 * Structured concurrency continued to evolve as a model for treating related
 * concurrent subtasks as one observable unit of work.
 * </p>
 */
public class StructuredConcurrencyFourthPreviewNotes {
	/**
	 * Explains the concurrency problem being refined by the preview.
	 *
	 * @return a short feature goal
	 */
	public String featureGoal() {
		return "treat related concurrent subtasks as one observable unit of work";
	}

	/**
	 * Describes the feature status across releases.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "fourth preview in Java 24 and fifth preview in Java 25";
	}

	/**
	 * Points learners to the next preview module.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 25 structured_concurrency module because this feature is still preview";
	}
}
