package net.jrodolfo.java_evolution.java14;

/**
 * Demonstrates pattern matching for {@code instanceof} as a Java 14 preview
 * feature.
 *
 * <p>
 * The feature became final in Java 16. The example uses final syntax because
 * this repository compiles with JDK 25.
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
