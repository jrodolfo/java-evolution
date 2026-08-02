package net.jrodolfo.java_evolution.java22;

/**
 * Explains statements before {@code super(...)} as a Java 22 preview feature.
 *
 * <p>
 * The feature allows constructor argument validation or preparation before an
 * explicit superclass constructor invocation, within strict safety rules.
 * </p>
 */
public class StatementsBeforeSuperPreviewNotes {

	public String purpose() {
		return "allow safe argument checking and preparation before calling an explicit superclass constructor";
	}

	public String limitation() {
		return "statements before super cannot use the instance being constructed";
	}

	public String projectDecision() {
		return "the repository documents this preview feature as notes to avoid preview-flag complexity";
	}
}
