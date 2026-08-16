package net.jrodolfo.java_evolution.java24.flexible_constructor_bodies;

/**
 * Explains flexible constructor bodies third preview in Java 24.
 *
 * <p>
 * Flexible constructor bodies let constructors perform safe validation or
 * preparation before explicit constructor invocation. Java 24 kept the feature
 * in preview before finalization in Java 25.
 * </p>
 *
 * <p>
 * This repository keeps the Java 24 step as notes because the Java 25 package
 * contains the final runnable example.
 * </p>
 */
public class FlexibleConstructorBodiesThirdPreviewNotes {
	/**
	 * Explains the constructor problem.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "constructors often need validation or normalization before delegating to another constructor";
	}

	/**
	 * Explains the older constructor restriction.
	 *
	 * @return a short explanation
	 */
	public String oldConstructorRule() {
		return "older Java required explicit constructor invocation with this(...) or super(...) to be the first statement";
	}

	/**
	 * Explains the flexible constructor model.
	 *
	 * @return a short explanation
	 */
	public String flexibleConstructorModel() {
		return "constructor code can perform safe preparation before explicit constructor delegation";
	}

	/**
	 * Explains the safety boundary that remains.
	 *
	 * @return a short explanation
	 */
	public String safetyRule() {
		return "the constructor still cannot use the object under construction before it is properly initialized";
	}

	/**
	 * Explains why Java 24 keeps this entry as a bridge note.
	 *
	 * @return a short explanation
	 */
	public String previewStep() {
		return "Java 24 was the third preview, after Java 23 and before finalization in Java 25";
	}

	/**
	 * Describes the Java 24 maturity level.
	 *
	 * @return a short status note
	 */
	public String thirdPreviewStatus() {
		return "flexible constructor bodies were in third preview in Java 24 and final in Java 25";
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
