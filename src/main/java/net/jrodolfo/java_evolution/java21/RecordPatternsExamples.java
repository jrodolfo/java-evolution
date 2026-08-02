package net.jrodolfo.java_evolution.java21;

/**
 * Demonstrates record patterns, finalized in Java 21.
 */
public class RecordPatternsExamples {

	/**
	 * Deconstructs nested records.
	 *
	 * @param shape the shape to describe
	 * @return a text description
	 */
	public String describe(Object shape) {
		if (shape instanceof Rectangle(Point(int x1, int y1), Point(int x2, int y2))) {
			return "rectangle from " + x1 + "," + y1 + " to " + x2 + "," + y2;
		}
		return "unknown";
	}

	public record Point(int x, int y) {
	}

	public record Rectangle(Point topLeft, Point bottomRight) {
	}
}
