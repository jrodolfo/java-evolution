package net.jrodolfo.java_evolution.java14;

/**
 * Demonstrates pattern matching for {@code instanceof} as a Java 14 preview
 * feature.
 *
 * <p>
 * Before this feature, type checks often required a separate cast after
 * {@code instanceof}. That repeated the type and created room for mistakes.
 * </p>
 *
 * <p>
 * Pattern matching for {@code instanceof} solves this by combining the type
 * check and local variable binding. The feature became final in Java 16. The
 * example uses final syntax because this repository compiles with JDK 26.
 * </p>
 */
public class PatternMatchingInstanceofPreviewExamples {

	/**
	 * Uses a type pattern to bind the checked value to a local variable.
	 *
	 * @param value value to inspect
	 * @return a label based on the runtime type
	 */
	public String describe(Object value) {
		if (value instanceof String text) {
			return "string length=" + text.length();
		}
		if (value instanceof Integer number) {
			return "integer doubled=" + number * 2;
		}
		return "unknown";
	}
}
