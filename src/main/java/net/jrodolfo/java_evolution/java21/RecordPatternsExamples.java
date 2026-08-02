package net.jrodolfo.java_evolution.java21;

/**
 * Demonstrates record patterns, finalized in Java 21.
 *
 * <p>
 * Records make transparent data carriers concise, but code often needs to read
 * the components back out. Record patterns let code test the type and bind
 * record components in one expression, which is especially useful for nested
 * records.
 * </p>
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

	/**
	 * Point record used by the nested deconstruction example.
	 *
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public record Point(int x, int y) {
	}

	/**
	 * Rectangle record made of two points.
	 *
	 * @param topLeft top-left point
	 * @param bottomRight bottom-right point
	 */
	public record Rectangle(Point topLeft, Point bottomRight) {
	}
}
