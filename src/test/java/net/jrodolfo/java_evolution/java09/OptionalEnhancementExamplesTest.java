package net.jrodolfo.java_evolution.java09;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class OptionalEnhancementExamplesTest {

	private final OptionalEnhancementExamples examples = new OptionalEnhancementExamples();

	@Test
	void orUsesPrimaryOptionalWhenPresent() {
		// Given
		Optional<String> primary = Optional.of("primary@example.com");
		Optional<String> fallback = Optional.of("fallback@example.com");

		// When
		Optional<String> email = examples.preferredEmail(primary, fallback);

		// Then
		assertThat(email)
				.as("Optional.or should keep the primary Optional when it is present")
				.contains("primary@example.com");
	}

	@Test
	void orUsesFallbackOptionalWhenPrimaryIsEmpty() {
		// Given
		Optional<String> primary = Optional.empty();
		Optional<String> fallback = Optional.of("fallback@example.com");

		// When
		Optional<String> email = examples.preferredEmail(primary, fallback);

		// Then
		assertThat(email)
				.as("Optional.or should evaluate the fallback when the primary Optional is empty")
				.contains("fallback@example.com");
	}

	@Test
	void ifPresentOrElseHandlesPresentAndMissingValues() {
		// When / Then
		assertThat(examples.describe(Optional.of("Java 9")))
				.as("ifPresentOrElse should run the Consumer branch when a value exists")
				.isEqualTo("value: Java 9");
		assertThat(examples.describe(Optional.empty()))
				.as("ifPresentOrElse should run the Runnable branch when no value exists")
				.isEqualTo("value missing");
	}

	@Test
	void optionalStreamFlattensPresentValuesIntoAStream() {
		// Given
		List<Optional<String>> values = Arrays.asList(
				Optional.of("modules"),
				Optional.empty(),
				Optional.of("streams"));

		// When
		List<String> presentValues = examples.presentValues(values);

		// Then
		assertThat(presentValues)
				.as("Optional.stream should contribute zero or one value to the pipeline")
				.containsExactly("modules", "streams");
	}
}
