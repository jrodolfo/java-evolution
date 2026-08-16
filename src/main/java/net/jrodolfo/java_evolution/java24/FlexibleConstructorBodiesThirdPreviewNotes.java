package net.jrodolfo.java_evolution.java24;

/**
 * Explains flexible constructor bodies third preview in Java 24.
 *
 * <p>
 * This preview continued the effort to let constructors perform safe validation
 * or preparation before explicit constructor invocation. The feature became
 * final in Java 25.
 * </p>
 */
public class FlexibleConstructorBodiesThirdPreviewNotes {
	/**
	 * Explains the constructor problem being refined by the preview.
	 *
	 * @return a short feature goal
	 */
	public String featureGoal() {
		return "allow safe validation or preparation before explicit constructor delegation";
	}

	/**
	 * Describes the feature status across releases.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "third preview in Java 24 and final in Java 25";
	}

	/**
	 * Points learners to the final Java 25 example.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read FlexibleConstructorBodiesExamples in Java 25 for the final runnable example";
	}
}
