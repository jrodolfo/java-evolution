package net.jrodolfo.java_evolution.java25;

/**
 * Explains primitive patterns third preview in Java 25.
 *
 * <p>
 * This preview continues the work of making primitive values participate in
 * Java's pattern matching model for {@code instanceof}, {@code switch}, and
 * related constructs.
 * </p>
 *
 * <p>
 * Earlier pattern matching was most natural for reference types. Primitive
 * values still needed separate casts, range checks, or default branches that did
 * not expose the unmatched value cleanly. This preview lets primitive type
 * patterns appear in more places, so code can ask whether a primitive
 * conversion is safe and bind the converted value when it is.
 * </p>
 *
 * <pre>{@code
 * if (value instanceof byte b) {
 *     // b is available only when value can be converted to byte without loss
 * }
 * }</pre>
 */
public class PrimitivePatternsThirdPreviewNotes {
	/**
	 * Explains the feature goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "make pattern matching more uniform by allowing primitive types in pattern contexts";
	}

	/**
	 * Explains the safety benefit of primitive patterns.
	 *
	 * @return a short safety explanation
	 */
	public String safetyGoal() {
		return "primitive patterns help test whether a primitive conversion is safe before binding the converted value";
	}

	/**
	 * Describes the Java 25 maturity level.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "primitive types in patterns, instanceof, and switch are in third preview in Java 25 and require --enable-preview";
	}
}
