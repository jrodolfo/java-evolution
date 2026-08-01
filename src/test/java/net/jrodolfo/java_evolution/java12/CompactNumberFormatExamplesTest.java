package net.jrodolfo.java_evolution.java12;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.jupiter.api.Test;

class CompactNumberFormatExamplesTest {

	private final CompactNumberFormatExamples examples = new CompactNumberFormatExamples();

	@Test
	void shortCompactNumberFormatsLargeValuesCompactly() {
		// When
		String formatted = examples.shortCompactNumber(1_200, Locale.US);

		// Then
		assertThat(formatted)
				.as("Short compact formatting should abbreviate large values")
				.isEqualTo("1K");
	}

	@Test
	void longCompactNumberFormatsLargeValuesWithWords() {
		// When
		String formatted = examples.longCompactNumber(1_200, Locale.US);

		// Then
		assertThat(formatted)
				.as("Long compact formatting should use locale-specific words")
				.isEqualTo("1 thousand");
	}
}
