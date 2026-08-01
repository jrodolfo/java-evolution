package net.jrodolfo.java_evolution.java12;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TeeingCollectorExamplesTest {

	private final TeeingCollectorExamples examples = new TeeingCollectorExamples();

	@Test
	void teeingCollectorCombinesMinimumAndMaximumCollectors() {
		// Given
		var numbers = Arrays.asList(10, 3, 25, 7);

		// When
		int range = examples.range(numbers);

		// Then
		assertThat(range)
				.as("Collectors.teeing should combine min and max results into one range")
				.isEqualTo(22);
	}

	@Test
	void teeingCollectorCanBuildACustomSummary() {
		// Given
		var numbers = Arrays.asList(10, 20, 30);

		// When
		TeeingCollectorExamples.NumberSummary summary = examples.summarize(numbers);

		// Then
		assertThat(summary.count())
				.as("One downstream collector should count the stream elements")
				.isEqualTo(3);
		assertThat(summary.average())
				.as("The other downstream collector should compute numeric statistics")
				.isEqualTo(20.0);
	}
}
