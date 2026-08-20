package net.jrodolfo.java_evolution.java07;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NumericLiteralExamplesTest {

	private final NumericLiteralExamples examples = new NumericLiteralExamples();

	@Test
	void binaryLiteralRepresentsSameIntegerValueAsDecimal() {
		assertThat(examples.readAndExecuteMask())
				.as("0b101 should represent the same int value as decimal 5")
				.isEqualTo(5);
	}

	@Test
	void underscoresImproveLiteralReadabilityWithoutChangingValue() {
		assertThat(examples.groupedLargeNumber())
				.as("Underscores in numeric literals should not change the numeric value")
				.isEqualTo(1000000000L);
	}
}
