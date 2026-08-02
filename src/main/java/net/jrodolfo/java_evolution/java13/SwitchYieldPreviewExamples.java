package net.jrodolfo.java_evolution.java13;

/**
 * Demonstrates {@code yield} in switch expressions as a Java 13 preview
 * refinement.
 *
 * <p>
 * Java 12 previewed switch expressions, but block branches still needed a
 * clear way to produce a value when they contained multiple statements.
 * </p>
 *
 * <p>
 * Java 13 adjusted the preview by adding {@code yield}. It solves the
 * readability problem by making the branch result explicit.
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
