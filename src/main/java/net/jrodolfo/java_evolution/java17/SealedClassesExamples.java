package net.jrodolfo.java_evolution.java17;

/**
 * Demonstrates sealed classes, finalized in Java 17.
 *
 * <p>
 * Before sealed classes, Java could not directly express a closed hierarchy in
 * the type system. A parent type was usually open to any accessible subtype,
 * even when the domain had a known finite set of valid implementations.
 * </p>
 *
 * <p>
 * A sealed type solves this by explicitly controlling which classes or
 * interfaces may implement or extend it. This makes domain hierarchies easier
 * to understand and safer to exhaustively handle.
 * </p>
 */
public class SealedClassesExamples {

	/**
	 * Calculates the area for a known sealed shape hierarchy.
	 *
	 * @param shape the shape to measure
	 * @return the calculated area
	 */
	public double area(Shape shape) {
		if (shape instanceof Circle circle) {
			return Math.PI * circle.radius() * circle.radius();
		}
		if (shape instanceof Rectangle rectangle) {
			return rectangle.width() * rectangle.height();
		}
		if (shape instanceof Square square) {
			return square.side() * square.side();
		}
		throw new IllegalArgumentException("unknown shape");
	}

	/**
	 * Sealed parent interface that permits only the listed implementations.
	 */
	public sealed interface Shape permits Circle, Rectangle, Square {
	}

	/**
	 * Final permitted implementation.
	 *
	 * @param radius the circle radius
	 */
	public record Circle(double radius) implements Shape {
	}

	/**
	 * Final permitted implementation.
	 *
	 * @param width the rectangle width
	 * @param height the rectangle height
	 */
	public record Rectangle(double width, double height) implements Shape {
	}

	/**
	 * Non-sealed implementation, showing that a permitted subtype may reopen the
	 * hierarchy.
	 */
	public non-sealed static class Square implements Shape {
		private final double side;

		/**
		 * Creates a square with the given side length.
		 *
		 * @param side the square side length
		 */
		public Square(double side) {
			this.side = side;
		}

		/**
		 * @return the square side length
		 */
		public double side() {
			return side;
		}
	}
}
