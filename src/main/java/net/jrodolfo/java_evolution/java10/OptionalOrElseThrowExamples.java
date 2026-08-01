package net.jrodolfo.java_evolution.java10;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Demonstrates the no-argument {@link Optional#orElseThrow()} method added in
 * Java 10.
 *
 * <p>
 * Before Java 10, callers commonly used {@code get()} or the Java 8
 * {@code orElseThrow(Supplier)} overload. Java 10 added a clearer no-argument
 * option for the common case where {@link NoSuchElementException} is acceptable.
 * </p>
 */
public class OptionalOrElseThrowExamples {

	/**
	 * Returns the value when the Optional is present.
	 *
	 * @param value the Optional to unwrap
	 * @return the contained value
	 */
	public String requiredValue(Optional<String> value) {
		return value.orElseThrow();
	}

	/**
	 * Looks up a feature by name and unwraps the result with no-arg
	 * {@link Optional#orElseThrow()}.
	 *
	 * @param featureName the feature to find
	 * @return a message for the requested feature
	 */
	public String describeRequiredFeature(String featureName) {
		return findFeatureDescription(featureName).orElseThrow();
	}

	private Optional<String> findFeatureDescription(String featureName) {
		if ("var".equals(featureName)) {
			return Optional.of("local variable type inference");
		}
		return Optional.empty();
	}
}
