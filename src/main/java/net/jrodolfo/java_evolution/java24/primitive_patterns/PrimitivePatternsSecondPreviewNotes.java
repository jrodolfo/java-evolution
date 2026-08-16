package net.jrodolfo.java_evolution.java24.primitive_patterns;

/**
 * Explains primitive patterns second preview in Java 24.
 *
 * <p>
 * Primitive patterns continue the work of making primitive values participate
 * more naturally in pattern matching, {@code instanceof}, and {@code switch}.
 * Java 24 kept the feature in preview for another round of feedback.
 * </p>
 *
 * <p>
 * This repository keeps the Java 24 step as notes because the feature is still
 * preview in this version range.
 * </p>
 */
public class PrimitivePatternsSecondPreviewNotes {
	/**
	 * Explains the pattern-matching problem.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "primitive values were less integrated with Java pattern matching than reference values";
	}

	/**
	 * Explains the older mental split.
	 *
	 * @return a short explanation
	 */
	public String olderModel() {
		return "reference values gained pattern matching while primitive values still relied on separate checks and conversions";
	}

	/**
	 * Explains the primitive-pattern model.
	 *
	 * @return a short explanation
	 */
	public String primitivePatternModel() {
		return "primitive values can participate in pattern matching, instanceof checks, and switch selection";
	}

	/**
	 * Explains why the feature is useful beyond syntax.
	 *
	 * @return a short explanation
	 */
	public String safetyGoal() {
		return "primitive patterns can express whether a primitive conversion is safe before binding a converted value";
	}

	/**
	 * Explains why Java 24 keeps this entry as a bridge note.
	 *
	 * @return a short explanation
	 */
	public String previewStep() {
		return "Java 24 was the second preview, after Java 23 and before the Java 25 third preview";
	}

	/**
	 * Describes the Java 24 maturity level.
	 *
	 * @return a short status note
	 */
	public String secondPreviewStatus() {
		return "primitive patterns were in second preview in Java 24 and third preview in Java 25";
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
