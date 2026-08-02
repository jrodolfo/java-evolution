package net.jrodolfo.java_evolution.java09;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Demonstrates {@link Optional} methods added in Java 9.
 *
 * <p>
 * Java 8 made missing return values explicit with {@link Optional}, but some
 * common workflows still needed awkward code: trying a fallback lookup,
 * handling present and missing branches together, and flattening many
 * {@code Optional} values into a stream.
 * </p>
 *
 * <p>
 * Java 9 added {@link Optional#or(java.util.function.Supplier)},
 * {@link Optional#ifPresentOrElse(java.util.function.Consumer, Runnable)}, and
 * {@link Optional#stream()} to make those workflows direct.
 * </p>
 */
public class OptionalEnhancementExamples {

	/**
	 * Uses {@link Optional#or(java.util.function.Supplier)} to try a fallback
	 * Optional only when the primary Optional is empty.
	 *
	 * @param primary the preferred value
	 * @param fallback the fallback value
	 * @return the primary value when present, otherwise the fallback value
	 */
	public Optional<String> preferredEmail(Optional<String> primary, Optional<String> fallback) {
		return primary.or(() -> fallback);
	}

	/**
	 * Uses {@link Optional#ifPresentOrElse(java.util.function.Consumer, Runnable)}
	 * to handle both branches explicitly.
	 *
	 * @param value the Optional to describe
	 * @return text explaining whether the value was present
	 */
	public String describe(Optional<String> value) {
		StringBuilder description = new StringBuilder();

		value.ifPresentOrElse(
				presentValue -> description.append("value: ").append(presentValue),
				() -> description.append("value missing"));

		return description.toString();
	}

	/**
	 * Uses {@link Optional#stream()} to flatten Optionals into a stream pipeline.
	 *
	 * @param values the Optional values to collect
	 * @return only the values that are present
	 */
	public List<String> presentValues(List<Optional<String>> values) {
		return values.stream()
				.flatMap(Optional::stream)
				.collect(Collectors.toList());
	}
}
