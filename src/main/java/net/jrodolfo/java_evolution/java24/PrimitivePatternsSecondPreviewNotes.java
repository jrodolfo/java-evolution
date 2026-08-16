package net.jrodolfo.java_evolution.java24;

/**
 * Explains primitive patterns second preview in Java 24.
 *
 * <p>
 * This preview continued the work of making primitive values participate more
 * naturally in pattern matching, {@code instanceof}, and {@code switch}.
 * </p>
 */
public class PrimitivePatternsSecondPreviewNotes {
	/**
	 * Explains the pattern-matching problem being refined by the preview.
	 *
	 * @return a short feature goal
	 */
	public String featureGoal() {
		return "let primitive values participate more naturally in pattern matching and switch";
	}

	/**
	 * Describes the feature status across releases.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "second preview in Java 24 and third preview in Java 25";
	}

	/**
	 * Points learners to the next preview.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read PrimitivePatternsThirdPreviewNotes in Java 25 because this feature is still preview";
	}
}
