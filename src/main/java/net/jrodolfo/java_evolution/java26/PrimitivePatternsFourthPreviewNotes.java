package net.jrodolfo.java_evolution.java26;

/**
 * Explains the fourth preview of primitive patterns in {@code instanceof} and
 * {@code switch}.
 *
 * <p>
 * Pattern matching began with reference types. Primitive patterns aim to make
 * the model more uniform while preserving safe conversion checks, so code can
 * test and bind primitive values without relying on lossy casts.
 * </p>
 */
public class PrimitivePatternsFourthPreviewNotes {

	/**
	 * Explains the problem addressed by primitive patterns.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "primitive values historically needed separate range checks, casts, or switch logic outside the pattern model";
	}

	/**
	 * Explains the feature idea.
	 *
	 * @return a short feature explanation
	 */
	public String idea() {
		return "primitive patterns combine a safe conversion test with a binding that can be used in instanceof and switch";
	}

	/**
	 * Describes the Java 26 maturity level.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "primitive patterns are a fourth preview language feature in Java 26";
	}
}
