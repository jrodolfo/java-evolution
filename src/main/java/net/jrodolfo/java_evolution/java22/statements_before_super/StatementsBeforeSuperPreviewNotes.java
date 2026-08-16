package net.jrodolfo.java_evolution.java22.statements_before_super;

/**
 * Explains statements before {@code super(...)} as a Java 22 preview feature.
 *
 * <p>
 * Before this feature, an explicit {@code super(...)} or {@code this(...)} call
 * had to be the first constructor statement. That made argument validation or
 * preparation awkward when the superclass constructor needed already-checked
 * values.
 * </p>
 *
 * <p>
 * Java 22 previewed a more flexible constructor body while preserving the
 * important safety rule: code before {@code super(...)} still cannot use the
 * instance being constructed.
 * </p>
 */
public class StatementsBeforeSuperPreviewNotes {

	/**
	 * Explains the old constructor rule.
	 *
	 * @return a short explanation
	 */
	public String oldRule() {
		return "an explicit super or this constructor call had to be the first constructor statement";
	}

	/**
	 * Explains the practical problem.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "constructor arguments sometimes need validation or preparation before delegation";
	}

	/**
	 * Names the key safety limitation.
	 *
	 * @return a short limitation
	 */
	public String safetyRule() {
		return "statements before super cannot use the instance being constructed";
	}

	/**
	 * Explains how the feature name evolved after the first preview.
	 *
	 * @return a short naming-history note
	 */
	public String namingEvolution() {
		return "Java 22 described statements before super, while later previews and Java 25 use the broader name Flexible Constructor Bodies";
	}

	/**
	 * Points learners to the final Java 25 example.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read FlexibleConstructorBodiesExamples in Java 25 for the final constructor feature";
	}

	/**
	 * Explains why this package uses notes.
	 *
	 * @return the project decision
	 */
	public String projectDecision() {
		return "the repository documents this Java 22 preview feature as notes to avoid preview-flag complexity";
	}
}
