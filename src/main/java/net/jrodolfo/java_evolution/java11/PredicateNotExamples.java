package net.jrodolfo.java_evolution.java11;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Demonstrates {@link Predicate#not(Predicate)}, introduced in Java 11.
 *
 * <p>
 * Before Java 11, negating a predicate in a stream often required a lambda such
 * as {@code value -> !value.isBlank()}. That prevented using a direct method
 * reference for the positive condition.
 * </p>
 *
 * <p>
 * {@link Predicate#not(Predicate)} solves this by adapting an existing
 * predicate or method reference into its opposite, keeping stream filters easy
 * to scan.
 * </p>
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
