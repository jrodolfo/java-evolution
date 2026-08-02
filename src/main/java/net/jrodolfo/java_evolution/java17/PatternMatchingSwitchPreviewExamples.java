package net.jrodolfo.java_evolution.java17;

/**
 * Demonstrates pattern matching for {@code switch} as a Java 17 preview
 * feature.
 *
 * <p>
 * Before pattern matching for switch, type-based branching often required a
 * chain of {@code if}, {@code instanceof}, and casts. That scattered the
 * dispatch logic across multiple statements.
 * </p>
 *
 * <p>
 * Pattern matching for switch solves this by letting a switch branch on type
 * patterns. It became final later, in Java 21. This project compiles on JDK
 * 25, so the example uses final syntax while documenting the Java 17 preview
 * origin.
 * </p>
 */
public class PatternMatchingSwitchPreviewExamples {

	/**
	 * Uses type patterns inside a switch expression.
	 *
	 * @param value the value to classify
	 * @return a type-specific description
	 */
	public String describe(Object value) {
		return switch (value) {
			case null -> "null";
			case String text when text.isBlank() -> "blank string";
			case String text -> "string length=" + text.length();
			case Integer number -> "integer doubled=" + number * 2;
			default -> "unknown";
		};
	}
}
