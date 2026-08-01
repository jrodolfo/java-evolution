package net.jrodolfo.java_evolution.java11;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class PredicateNotExamplesTest {

	private final PredicateNotExamples examples = new PredicateNotExamples();

	@Test
	void predicateNotMakesNegatedMethodReferencesReadable() {
		// Given
		List<String> values = Arrays.asList("Java", " ", "11", "");

		// When
		List<String> nonBlankValues = examples.nonBlankValues(values);

		// Then
		assertThat(nonBlankValues)
				.as("Predicate.not(String::isBlank) should keep only non-blank values")
				.containsExactly("Java", "11");
	}
}
