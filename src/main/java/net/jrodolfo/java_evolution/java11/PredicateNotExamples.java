package net.jrodolfo.java_evolution.java11;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Demonstrates {@link Predicate#not(Predicate)}, introduced in Java 11.
 */
public class PredicateNotExamples {

	/**
	 * Removes blank strings with {@code Predicate.not(String::isBlank)}.
	 *
	 * @param values the values to filter
	 * @return only non-blank values
	 */
	public List<String> nonBlankValues(List<String> values) {
		return values.stream()
				.filter(Predicate.not(String::isBlank))
				.collect(Collectors.toList());
	}
}
