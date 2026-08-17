package net.jrodolfo.java_evolution.java21;

/**
 * Demonstrates unnamed patterns and variables as a Java 21 preview feature.
 *
 * <p>
 * Sometimes Java syntax requires a variable name even though the program
 * intentionally ignores the value. Before this feature, developers often used
 * names such as {@code ignored} or {@code unused}. The underscore makes that
 * intent part of the language: the value is deliberately not available for
 * later use.
 * </p>
 *
 * <p>
 * The feature became final in Java 22 as unnamed variables and patterns. This
 * project compiles on JDK 25, where the same syntax is final.
 * </p>
 */
public class UnnamedPatternsVariablesPreviewExamples {

	/**
	 * Uses an unnamed variable where the value is intentionally ignored.
	 *
	 * <p>
	 * The loop visits every element, but the body only needs to count visits. The
	 * underscore says the element value is irrelevant.
	 * </p>
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
	 * <p>
	 * The code only needs to know that parsing failed. It does not need the
	 * exception object itself.
	 * </p>
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
