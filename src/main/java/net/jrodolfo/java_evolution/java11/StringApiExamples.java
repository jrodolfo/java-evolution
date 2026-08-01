package net.jrodolfo.java_evolution.java11;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Demonstrates String API additions introduced in Java 11.
 *
 * <p>
 * Java 11 added small but useful methods for blank checks, line processing,
 * Unicode-aware stripping, and repeating text.
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
