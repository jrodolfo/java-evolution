package net.jrodolfo.java_evolution.java24;

/**
 * Explains scoped values fourth preview in Java 24.
 *
 * <p>
 * Scoped values continued to refine immutable context propagation through a
 * bounded dynamic scope. The feature became final in Java 25.
 * </p>
 */
public class ScopedValuesFourthPreviewNotes {
	/**
	 * Explains the context-propagation problem being refined by the preview.
	 *
	 * @return a short feature goal
	 */
	public String featureGoal() {
		return "share immutable contextual data through a bounded dynamic scope";
	}

	/**
	 * Describes the feature status across releases.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "fourth preview in Java 24 and final in Java 25";
	}

	/**
	 * Points learners to the final Java 25 module.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 25 scoped_values module for the final runnable learning example";
	}
}
