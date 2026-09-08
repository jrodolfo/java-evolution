package net.jrodolfo.java_evolution.java19;

/**
 * Demonstrates record patterns as a Java 19 preview feature.
 *
 * <p>
 * Records made data-carrier classes concise, but extracting values still
 * required accessor calls after checking the type. Record patterns solve that
 * by letting code match and deconstruct a record in the same expression.
 * </p>
 *
 * <p>
 * Record patterns became final later, in Java 21. The record-pattern form
 * shown here remains syntactically and semantically faithful to the Java 19
 * preview while documenting that preview origin.
 * </p>
 */
public class RecordPatternsPreviewExamples {

	/**
	 * Deconstructs a record with an {@code instanceof} record pattern.
	 *
	 * @param value the value to inspect
	 * @return a formatted point description
	 */
	public String describe(Object value) {
		if (value instanceof Point(int x, int y)) {
			return "point x=" + x + " y=" + y;
		}
		return "unknown";
	}

	/**
	 * Small record used by the record pattern example.
	 *
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public record Point(int x, int y) {
	}
}
