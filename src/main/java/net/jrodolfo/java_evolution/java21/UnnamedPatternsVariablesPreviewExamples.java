package net.jrodolfo.java_evolution.java21;

/**
 * Demonstrates unnamed patterns and variables as a Java 21 preview feature.
 *
 * <p>
 * Sometimes Java syntax requires a variable name even though the program
 * intentionally ignores the value. Before this feature, developers often used
 * names such as {@code ignored} or {@code unused}. The underscore makes that
 * intent part of the language.
 * </p>
 *
 * <p>
 * The feature became final in Java 22 as unnamed variables and patterns. This
 * example uses JDK 25-compatible syntax.
 * </p>
 */
public class UnnamedPatternsVariablesPreviewExamples {

	/**
	 * Uses an unnamed variable where the value is intentionally ignored.
	 *
	 * @param values values to count
	 * @return how many values were visited
	 */
	public int countWithoutUsingElements(Iterable<String> values) {
		int count = 0;
		for (String _ : values) {
			count++;
		}
		return count;
	}

	/**
	 * Uses an unnamed catch parameter when the exception object is not needed.
	 *
	 * @param text numeric text
	 * @return whether the text can be parsed
	 */
	public boolean canParseInteger(String text) {
		try {
			Integer.parseInt(text);
			return true;
		}
		catch (NumberFormatException _) {
			return false;
		}
	}
}
