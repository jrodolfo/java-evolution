package net.jrodolfo.java_evolution.java12;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SwitchExpressionPreviewExamplesTest {

	private final SwitchExpressionPreviewExamples examples = new SwitchExpressionPreviewExamples();

	@Test
	void switchExpressionReturnsAValueDirectly() {
		// When / Then
		assertThat(examples.dayType(1))
				.as("Java 12 preview switch expressions allowed switch to produce a value")
				.isEqualTo("weekday");
		assertThat(examples.dayType(7))
				.as("Multiple labels can share the same arrow branch")
				.isEqualTo("weekend");
		assertThat(examples.dayType(99))
				.as("The default branch should handle unknown values")
				.isEqualTo("unknown");
	}
}
