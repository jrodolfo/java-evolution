package net.jrodolfo.java_evolution.java05;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

/**
 * Demonstrates static import, introduced in Java 5.
 */
public class StaticImportExamples {

	/**
	 * Uses statically imported {@link Math} members for a compact formula.
	 *
	 * @param radius circle radius
	 * @return circle area
	 */
	public double circleArea(double radius) {
		return PI * pow(radius, 2);
	}
}
