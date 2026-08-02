package net.jrodolfo.java_evolution.java14;

/**
 * Demonstrates switch expressions, finalized in Java 14.
 *
 * <p>
 * Before switch expressions, returning a value from {@code switch} usually
 * required mutable variables, repeated assignments, and careful
 * {@code break} statements.
 * </p>
 *
 * <p>
 * Switch expressions solve this by allowing {@code switch} to produce a value
 * directly. They also support arrow labels, which avoid accidental
 * fall-through. Java 14 finalized the feature after previews in Java 12 and
 * Java 13.
 * </p>
 */
public class SwitchExpressionExamples {

	/**
	 * Converts a month number into a season using a switch expression.
	 *
	 * @param month month number from 1 to 12
	 * @return the season name, or {@code unknown} for invalid values
	 */
	public String season(int month) {
		return switch (month) {
			case 12, 1, 2 -> "winter";
			case 3, 4, 5 -> "spring";
			case 6, 7, 8 -> "summer";
			case 9, 10, 11 -> "fall";
			default -> "unknown";
		};
	}

	/**
	 * Uses {@code yield} when a switch branch needs a block with more than one
	 * statement.
	 *
	 * @param score numeric score
	 * @return a grade label
	 */
	public String grade(int score) {
		return switch (score / 10) {
			case 10, 9 -> "excellent";
			case 8 -> "great";
			case 7 -> {
				String label = "good";
				yield label + " progress";
			}
			default -> "keep practicing";
		};
	}
}
