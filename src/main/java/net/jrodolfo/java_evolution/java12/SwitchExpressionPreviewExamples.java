package net.jrodolfo.java_evolution.java12;

/**
 * Demonstrates switch expressions as a Java 12 preview feature.
 *
 * <p>
 * Before switch expressions, returning a value from {@code switch} usually
 * required a mutable local variable and careful {@code break} statements.
 * Accidental fall-through was also a common source of bugs.
 * </p>
 *
 * <p>
 * Java 12 previewed switch expressions to solve those problems. Its first
 * preview included value-bearing {@code break}; Java 13 replaced that form
 * with {@code yield}, and Java 14 finalized switch expressions. The runnable
 * method uses the current syntax so it can compile on JDK 26, while
 * {@link #firstPreviewSource()} preserves the historically important Java 12
 * form as source text.
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

	/**
	 * Returns a minimal source example of the Java 12 first-preview form.
	 *
	 * <p>The text is intentionally not compiled by the main build: a modern
	 * compiler cannot use {@code --release 12 --enable-preview} to compile a
	 * preview feature from an older JDK release.</p>
	 *
	 * @return Java 12 switch-expression source using value-bearing {@code break}
	 */
	public String firstPreviewSource() {
		return "String result = switch (dayNumber) {\n"
				+ "    case 1, 2, 3, 4, 5: break \"weekday\";\n"
				+ "    case 6, 7: break \"weekend\";\n"
				+ "    default: break \"unknown\";\n"
				+ "};";
	}
}
