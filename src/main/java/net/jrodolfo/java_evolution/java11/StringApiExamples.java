package net.jrodolfo.java_evolution.java11;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Demonstrates String API additions introduced in Java 11.
 *
 * <p>
 * Before Java 11, common string operations such as checking for blank text,
 * splitting into lines, stripping Unicode-aware whitespace, and repeating text
 * often required helper code or less precise alternatives.
 * </p>
 *
 * <p>
 * Java 11 added small but useful methods for those cases: {@link String#isBlank()},
 * {@link String#lines()}, {@link String#strip()}, and {@link String#repeat(int)}.
 * </p>
 */
public class StringApiExamples {

	/**
	 * Uses {@link String#isBlank()} to detect text that contains only whitespace.
	 *
	 * @param text the text to inspect
	 * @return whether the text is empty or contains only whitespace
	 */
	public boolean isBlankText(String text) {
		return text.isBlank();
	}

	/**
	 * Uses {@link String#lines()} to split text into a stream of lines.
	 *
	 * @param text multi-line text
	 * @return the lines collected in encounter order
	 */
	public List<String> lines(String text) {
		return text.lines().collect(Collectors.toList());
	}

	/**
	 * Uses {@link String#strip()} to remove leading and trailing Unicode
	 * whitespace.
	 *
	 * @param text the text to strip
	 * @return stripped text
	 */
	public String stripText(String text) {
		return text.strip();
	}

	/**
	 * Uses {@link String#stripLeading()} to remove Unicode whitespace only from
	 * the beginning of the text.
	 *
	 * @param text the text to strip
	 * @return text without leading whitespace
	 */
	public String stripLeadingText(String text) {
		return text.stripLeading();
	}

	/**
	 * Uses {@link String#stripTrailing()} to remove Unicode whitespace only from
	 * the end of the text.
	 *
	 * @param text the text to strip
	 * @return text without trailing whitespace
	 */
	public String stripTrailingText(String text) {
		return text.stripTrailing();
	}

	/**
	 * Uses {@link String#repeat(int)} to repeat a string.
	 *
	 * @param text the text to repeat
	 * @param times how many times to repeat it
	 * @return the repeated text
	 */
	public String repeatText(String text, int times) {
		return text.repeat(times);
	}
}
