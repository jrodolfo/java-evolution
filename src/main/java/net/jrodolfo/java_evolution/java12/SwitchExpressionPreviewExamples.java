package net.jrodolfo.java_evolution.java12;

/**
 * Demonstrates switch expressions as a Java 12 preview feature.
 *
 * <p>
 * Switch expressions became final later, in Java 14. This project compiles on
 * JDK 25, so the example uses the final syntax while documenting that Java 12
 * was the first preview release.
 * </p>
 */
public class SwitchExpressionPreviewExamples {

	/**
	 * Uses a switch expression to return a value directly.
	 *
	 * @param dayNumber day number from 1 to 7
	 * @return whether the day is a weekday or weekend
	 */
	public String dayType(int dayNumber) {
		return switch (dayNumber) {
			case 1, 2, 3, 4, 5 -> "weekday";
			case 6, 7 -> "weekend";
			default -> "unknown";
		};
	}
}
