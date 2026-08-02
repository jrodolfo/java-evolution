package net.jrodolfo.java_evolution.java17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RandomGeneratorExamplesTest {

	private final RandomGeneratorExamples examples = new RandomGeneratorExamples();

	@Test
	void randomGeneratorFactoryFindsNamedAlgorithms() {
		assertThat(examples.algorithmExists("L64X128MixRandom"))
				.as("Java 17 should expose named RandomGenerator algorithms")
				.isTrue();
	}

	@Test
	void namedGeneratorCanProduceBoundedValues() {
		// When
		int value = examples.boundedRandomValue("L64X128MixRandom", 10);

		// Then
		assertThat(value)
				.as("A bounded random value should be inside the requested range")
				.isBetween(0, 9);
	}
}
