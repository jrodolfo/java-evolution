package net.jrodolfo.java_evolution.java21;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RecordPatternsExamplesTest {

	private final RecordPatternsExamples examples = new RecordPatternsExamples();

	@Test
	void recordPatternDeconstructsNestedRecords() {
		var rectangle = new RecordPatternsExamples.Rectangle(
				new RecordPatternsExamples.Point(0, 0),
				new RecordPatternsExamples.Point(10, 20));

		String description = examples.describe(rectangle);

		assertThat(description)
				.as("Record patterns should deconstruct nested record components")
				.isEqualTo("rectangle from 0,0 to 10,20");
	}

	@Test
	void nonMatchingValueKeepsTheFallbackPathVisible() {
		Object notARectangle = "not a rectangle";

		String description = examples.describe(notARectangle);

		assertThat(description)
				.as("The record pattern only binds components when the value has the expected record shape")
				.isEqualTo("unknown");
	}
}
