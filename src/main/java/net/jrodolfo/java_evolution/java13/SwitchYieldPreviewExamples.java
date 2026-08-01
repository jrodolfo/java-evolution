package net.jrodolfo.java_evolution.java13;

/**
 * Demonstrates {@code yield} in switch expressions as a Java 13 preview
 * refinement.
 *
 * <p>
 * Java 13 adjusted the switch expression preview by adding {@code yield} for
 * block branches that need to produce a value.
 * </p>
 */
public class SwitchYieldPreviewExamples {

	/**
	 * Uses {@code yield} when a switch branch needs multiple statements.
	 *
	 * @param score a numeric score
	 * @return a grade label
	 */
	public String grade(int score) {
		return switch (score / 10) {
			case 10, 9 -> "excellent";
			case 8 -> "great";
			case 7 -> {
				String base = "good";
				yield base + " progress";
			}
			default -> "keep practicing";
		};
	}
}
