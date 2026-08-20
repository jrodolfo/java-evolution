package net.jrodolfo.java_evolution.java04;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Demonstrates {@code java.util.regex}, introduced in J2SE 1.4.
 */
public class RegexExamples {

	/**
	 * Extracts all simple issue identifiers from text.
	 *
	 * @param text text to scan
	 * @return matched identifiers
	 */
	public List issueIds(String text) {
		Pattern pattern = Pattern.compile("[A-Z]+-\\d+");
		Matcher matcher = pattern.matcher(text);
		List ids = new ArrayList();

		while (matcher.find()) {
			ids.add(matcher.group());
		}

		return ids;
	}

	/**
	 * Validates a simple release package name.
	 *
	 * @param packageName package name to validate
	 * @return whether the name matches {@code javaNN}
	 */
	public boolean isReleasePackageName(String packageName) {
		return Pattern.matches("java\\d{2}", packageName);
	}
}
