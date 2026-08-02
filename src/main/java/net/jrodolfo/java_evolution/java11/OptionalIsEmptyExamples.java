package net.jrodolfo.java_evolution.java11;

import java.util.Optional;

/**
 * Demonstrates {@link Optional#isEmpty()}, introduced in Java 11.
 *
 * <p>
 * Before Java 11, checking for absence usually meant writing
 * {@code !optional.isPresent()}. That works, but it expresses the idea through
 * negation.
 * </p>
 *
 * <p>
 * {@code isEmpty()} solves this readability problem by naming the empty case
 * directly.
 * </p>
 */
public class OptionalIsEmptyExamples {

	/**
	 * Uses {@link Optional#isEmpty()} as the direct opposite of
	 * {@link Optional#isPresent()}.
	 *
	 * @param value the Optional to inspect
	 * @return whether the Optional has no value
	 */
	public boolean isMissing(Optional<String> value) {
		return value.isEmpty();
	}
}
