package net.jrodolfo.java_evolution.java20;

/**
 * Demonstrates pattern matching for switch as refined in the Java 20 fourth
 * preview.
 *
 * <p>
 * Pattern matching for {@code switch} went through multiple preview rounds
 * because it needed careful rules for null handling, guarded patterns,
 * exhaustiveness, and interaction with sealed types. Java 20 continued that
 * refinement before the feature became final in Java 21.
 * </p>
 *
 * <p>
 * This class uses final JDK 25 syntax while documenting the Java 20 preview
 * origin.
 * </p>
 */
public class PatternMatchingSwitchFourthPreviewExamples {

	/**
	 * Uses switch patterns to classify values.
	 *
	 * @param value the value to classify
	 * @return a type-specific description
	 */
	public String classify(Object value) {
		return switch (value) {
			case null -> "null";
			case String text when text.length() > 5 -> "long string";
			case String text -> "short string: " + text;
			case Integer number -> "integer: " + number;
			default -> "unknown";
		};
	}
}
