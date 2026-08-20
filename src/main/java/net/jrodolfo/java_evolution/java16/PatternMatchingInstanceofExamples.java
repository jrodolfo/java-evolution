package net.jrodolfo.java_evolution.java16;

/**
 * Demonstrates pattern matching for {@code instanceof}, finalized in Java 16.
 *
 * <p>
 * Before this feature, type checks usually required a separate cast after
 * {@code instanceof}. That repeated the type and made simple type-dependent
 * logic more verbose than necessary.
 * </p>
 *
 * <pre>{@code
 * // Before Java 16
 * if (value instanceof String) {
 *     String text = (String) value;
 *     return text.length();
 * }
 *
 * // Java 16
 * if (value instanceof String text) {
 *     return text.length();
 * }
 * }</pre>
 *
 * <p>
 * Pattern matching solves this by combining the type check and local variable
 * binding in one expression.
 * </p>
 */
public class PatternMatchingInstanceofExamples {

	/**
	 * Uses {@code instanceof} patterns to avoid a separate cast after a type
	 * check.
	 *
	 * @param value the value to inspect
	 * @return a type-specific description
	 */
	public String describe(Object value) {
		if (value instanceof String text && !text.isBlank()) {
			return "text length=" + text.length();
		}
		if (value instanceof Number number) {
			return "number=" + number.intValue();
		}
		return "unknown";
	}
}
