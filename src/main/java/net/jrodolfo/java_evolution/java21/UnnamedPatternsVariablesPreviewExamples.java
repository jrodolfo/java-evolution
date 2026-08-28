package net.jrodolfo.java_evolution.java21;

/**
 * Demonstrates unnamed patterns and variables as a Java 21 preview feature.
 *
 * <p>
 * Sometimes Java syntax requires a variable name even though the program
 * intentionally ignores the value. Before this feature, developers often used
 * names such as {@code ignored} or {@code unused}. The underscore makes that
 * intent part of the language: the value is deliberately not available for
 * later use.
 * </p>
 *
 * <p>
 * The feature became final in Java 22 as unnamed variables and patterns. This
 * project compiles on JDK 26, where the same syntax is final.
 * </p>
 */
public class UnnamedPatternsVariablesPreviewExamples {

	/**
	 * Uses an unnamed variable where the value is intentionally ignored.
	 *
	 * <p>
	 * The loop visits every element, but the body only needs to count visits. The
	 * underscore says the element value is irrelevant.
	 * </p>
	 *
	 * @param values values to count
	 * @return how many values were visited
	 */
	public int countWithoutUsingElements(Iterable<String> values) {
		int count = 0;
		for (String _ : values) {
			count++;
		}
		return count;
	}

	/**
	 * Uses an unnamed catch parameter when the exception object is not needed.
	 *
	 * <p>
	 * The code only needs to know that parsing failed. It does not need the
	 * exception object itself.
	 * </p>
	 *
	 * @param text numeric text
	 * @return whether the text can be parsed
	 */
	public boolean canParseInteger(String text) {
		try {
			Integer.parseInt(text);
			return true;
		}
		catch (NumberFormatException _) {
			return false;
		}
	}

	/**
	 * Uses unnamed patterns for record components that are not needed.
	 *
	 * <p>
	 * The first point's x coordinate is useful, but neither y coordinate nor the
	 * second point is needed. The underscores make those intentional omissions
	 * visible in the pattern.
	 * </p>
	 *
	 * @param shape value to inspect
	 * @param expectedStartX x coordinate required for the first point
	 * @return whether the value is a line whose first point has the requested x coordinate
	 */
	public boolean startsAtX(Object shape, int expectedStartX) {
		return shape instanceof Line(Point(int startX, _), _) && startX == expectedStartX;
	}

	/**
	 * Point record used by the unnamed record-pattern example.
	 *
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public record Point(int x, int y) {
	}

	/**
	 * Line record used by the unnamed record-pattern example.
	 *
	 * @param start starting point
	 * @param end ending point
	 */
	public record Line(Point start, Point end) {
	}
}
