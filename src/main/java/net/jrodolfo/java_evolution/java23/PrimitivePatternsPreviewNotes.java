package net.jrodolfo.java_evolution.java23;

/**
 * Explains primitive types in patterns, {@code instanceof}, and {@code switch}
 * as a Java 23 preview feature.
 *
 * <p>
 * Pattern matching had already improved reference-type checks and deconstruction
 * with {@code instanceof}, records, and {@code switch}. Primitive values were
 * still less integrated. This preview moves Java toward a more uniform pattern
 * model for both reference and primitive values.
 * </p>
 */
public class PrimitivePatternsPreviewNotes {

	/**
	 * Explains the feature goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "extend pattern matching so primitive values can participate in instanceof and switch checks";
	}

	/**
	 * Points learners to the next preview in this repository.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read PrimitivePatternsSecondPreviewNotes in Java 24 and PrimitivePatternsThirdPreviewNotes in Java 25 because this feature is still preview";
	}

	/**
	 * Describes the preview status in this repository's version range.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "preview in Java 23 and still preview in Java 25";
	}
}
