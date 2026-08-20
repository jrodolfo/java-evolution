package net.jrodolfo.java_evolution.java05;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FormattingExamplesTest {

	private final FormattingExamples examples = new FormattingExamples();

	@Test
	void stringFormatAppliesNumericPrecisionAndGrouping() {
		assertThat(examples.formatAmount(12345.678))
				.as("Formatter syntax should control grouping and decimal precision")
				.isEqualTo("12,345.68");
	}

	@Test
	void formatterCanReferenceArgumentsByPosition() {
		assertThat(examples.reverseOrder("first", "second"))
				.as("Explicit argument indexes should let a format string reuse or reorder inputs")
				.isEqualTo("second before first");
	}
}
