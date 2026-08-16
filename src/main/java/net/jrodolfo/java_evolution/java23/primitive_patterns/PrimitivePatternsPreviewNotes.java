package net.jrodolfo.java_evolution.java23.primitive_patterns;

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
	public String problemSolved() {
		return "primitive values were less integrated with Java pattern matching than reference values";
	}

	/**
	 * Explains what Java 23 previewed.
	 *
	 * @return a short explanation
	 */
	public String whatJavaPreviewed() {
		return "primitive values can participate in pattern matching, instanceof, and switch checks";
	}

	/**
	 * Explains the broader pattern-matching direction.
	 *
	 * @return a short explanation
	 */
	public String languageDirection() {
		return "Java is moving toward a more uniform pattern model for reference and primitive values";
	}

	/**
	 * Explains an important safety goal.
	 *
	 * @return a short safety note
	 */
	public String safetyGoal() {
		return "primitive patterns help test whether a primitive conversion is safe before binding the converted value";
	}

	/**
	 * Points learners to the next previews in this repository.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read the Java 24 primitive_patterns module and PrimitivePatternsThirdPreviewNotes in Java 25 because this feature is still preview";
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
