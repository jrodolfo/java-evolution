package net.jrodolfo.java_evolution.java16;

/**
 * Demonstrates pattern matching for {@code instanceof}, finalized in Java 16.
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
