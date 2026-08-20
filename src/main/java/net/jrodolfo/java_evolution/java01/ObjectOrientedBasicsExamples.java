package net.jrodolfo.java_evolution.java01;

/**
 * Refreshes early Java object-oriented basics: classes, objects, inheritance,
 * overriding, and encapsulation.
 */
public class ObjectOrientedBasicsExamples {

	/**
	 * Uses polymorphism through a superclass reference.
	 *
	 * @return the subclass implementation selected at runtime
	 */
	public String dispatchThroughSuperclass() {
		Shape shape = new Rectangle(4, 3);
		return shape.description();
	}

	/**
	 * Uses encapsulated state exposed through behavior.
	 *
	 * @return calculated area
	 */
	public int rectangleArea() {
		Rectangle rectangle = new Rectangle(5, 2);
		return rectangle.area();
	}

	static class Shape {
		String description() {
			return "shape";
		}
	}

	static class Rectangle extends Shape {
		private final int width;
		private final int height;

		Rectangle(int width, int height) {
			this.width = width;
			this.height = height;
		}

		@Override
		String description() {
			return "rectangle " + width + "x" + height;
		}

		int area() {
			return width * height;
		}
	}
}
