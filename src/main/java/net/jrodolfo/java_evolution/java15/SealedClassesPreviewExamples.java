package net.jrodolfo.java_evolution.java15;

/**
 * Demonstrates sealed classes as a Java 15 preview feature.
 *
 * <p>
 * Before sealed classes, a type hierarchy was usually open to any subtype that
 * could access the parent type. That made it difficult to model small closed
 * domains directly in the type system.
 * </p>
 *
 * <p>
 * Sealed classes solve this by letting the parent type explicitly list
 * permitted subtypes. They became final in Java 17. This project compiles on
 * JDK 25, so the example uses final syntax while documenting the Java 15
 * preview origin.
 * </p>
 */
public class SealedClassesPreviewExamples {

	/**
	 * Calculates the area of a known shape hierarchy.
	 *
	 * @param shape the shape to measure
	 * @return the shape area
	 */
	public double area(Shape shape) {
		if (shape instanceof Circle circle) {
			return Math.PI * circle.radius() * circle.radius();
		}
		if (shape instanceof Rectangle rectangle) {
			return rectangle.width() * rectangle.height();
		}
		throw new IllegalArgumentException("unknown shape");
	}

	/**
	 * Sealed parent type that explicitly lists permitted implementations.
	 */
	public sealed interface Shape permits Circle, Rectangle {
	}

	/**
	 * Circle implementation permitted by {@link Shape}.
	 *
	 * @param radius the circle radius
	 */
	public record Circle(double radius) implements Shape {
	}

	/**
	 * Rectangle implementation permitted by {@link Shape}.
	 *
	 * @param width the rectangle width
	 * @param height the rectangle height
	 */
	public record Rectangle(double width, double height) implements Shape {
	}
}
