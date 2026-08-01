package net.jrodolfo.java_evolution.java11;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class OptionalIsEmptyExamplesTest {

	private final OptionalIsEmptyExamples examples = new OptionalIsEmptyExamples();

	@Test
	void isEmptyDirectlyExpressesMissingOptionalValue() {
		// When / Then
		assertThat(examples.isMissing(Optional.empty()))
				.as("Optional.isEmpty should return true for an empty Optional")
				.isTrue();
		assertThat(examples.isMissing(Optional.of("Java 11")))
				.as("Optional.isEmpty should return false when a value is present")
				.isFalse();
	}
}
