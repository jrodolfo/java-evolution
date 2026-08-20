package net.jrodolfo.java_evolution.java13;

/**
 * Demonstrates the Java 13 preview refinement that introduced {@code yield} in
 * switch expressions.
 *
 * <p>
 * Java 12 previewed switch expressions and proposed using a {@code break}
 * statement with a value when a branch needed to produce a result. Feedback
 * from that preview led Java 13 to replace that design with {@code yield}.
 * Block branches still needed a clear way to produce a value when they
 * contained multiple statements.
 * </p>
 *
 * <p>
 * Java 13 adjusted the switch-expression preview by adding {@code yield}. It
 * solves the readability problem by making the branch result explicit. Java 14
 * later finalized switch expressions with this design.
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
