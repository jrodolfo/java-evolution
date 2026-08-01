package net.jrodolfo.java_evolution.java10;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class OptionalOrElseThrowExamplesTest {

	private final OptionalOrElseThrowExamples examples = new OptionalOrElseThrowExamples();

	@Test
	void noArgumentOrElseThrowReturnsPresentValue() {
		// Given
		Optional<String> value = Optional.of("Java 10");

		// When
		String result = examples.requiredValue(value);

		// Then
		assertThat(result)
				.as("Optional.orElseThrow() should return the value when it is present")
				.isEqualTo("Java 10");
	}

	@Test
	void noArgumentOrElseThrowFailsForEmptyOptional() {
		// Given
		Optional<String> value = Optional.empty();

		// When / Then
		assertThatThrownBy(() -> examples.requiredValue(value))
				.as("Optional.orElseThrow() should throw NoSuchElementException when empty")
				.isInstanceOf(NoSuchElementException.class);
	}

	@Test
	void noArgumentOrElseThrowCanBeUsedAfterALookup() {
		// When
		String description = examples.describeRequiredFeature("var");

		// Then
		assertThat(description)
				.as("The lookup should return a present Optional for a known feature")
				.isEqualTo("local variable type inference");
	}

	@Test
	void lookupWithNoResultThrowsNoSuchElementException() {
		assertThatThrownBy(() -> examples.describeRequiredFeature("unknown"))
				.as("The no-argument overload should use the standard NoSuchElementException")
				.isInstanceOf(NoSuchElementException.class);
	}
}
