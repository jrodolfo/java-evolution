package net.jrodolfo.java_evolution.java22;

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
 * The feature allows constructor argument validation or preparation before an
 * explicit superclass constructor invocation, within strict safety rules.
 * </p>
 */
public class StatementsBeforeSuperPreviewNotes {

	/**
	 * Explains the practical goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "allow safe argument checking and preparation before calling an explicit superclass constructor";
	}

	/**
	 * Names the key safety limitation.
	 *
	 * @return a short limitation
	 */
	public String limitation() {
		return "statements before super cannot use the instance being constructed";
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
		return "the repository documents this preview feature as notes to avoid preview-flag complexity";
	}
}
