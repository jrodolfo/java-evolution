package net.jrodolfo.java_evolution.java23.flexible_constructor_bodies;

/**
 * Explains flexible constructor bodies as a Java 23 second preview feature.
 *
 * <p>
 * Constructor code often needs to validate or prepare arguments before
 * delegating to another constructor. Older Java syntax forced an explicit
 * constructor invocation to appear first, so this validation had to be moved
 * elsewhere. Flexible constructor bodies relax that rule while preserving
 * initialization safety.
 * </p>
 */
public class FlexibleConstructorBodiesSecondPreviewNotes {

	/**
	 * Explains the constructor problem addressed by the feature.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "constructors sometimes need validation or preparation before explicit constructor invocation";
	}

	/**
	 * Explains the relationship to the Java 22 preview.
	 *
	 * @return a short explanation
	 */
	public String java22Connection() {
		return "Java 22 previewed statements before super and Java 23 continued the feature as flexible constructor bodies";
	}

	/**
	 * Names the constructor safety rule.
	 *
	 * @return a short safety note
	 */
	public String safetyRule() {
		return "constructor code before delegation still cannot use the partially constructed object unsafely";
	}

	/**
	 * Points learners to the final Java 25 example.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read FlexibleConstructorBodiesExamples in Java 25 for the final runnable example";
	}

	/**
	 * Describes the feature status across releases.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "second preview in Java 23, third preview in Java 24, and final in Java 25";
	}
}
