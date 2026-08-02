package net.jrodolfo.java_evolution.java20;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RecordPatternsSecondPreviewExamplesTest {

	private final RecordPatternsSecondPreviewExamples examples = new RecordPatternsSecondPreviewExamples();

	@Test
	void nestedRecordPatternDeconstructsNestedRecords() {
		// Given
		var address = new RecordPatternsSecondPreviewExamples.Address("Halifax", "Canada");
		var customer = new RecordPatternsSecondPreviewExamples.Customer("Rodolfo", address);

		// When / Then
		assertThat(examples.describe(customer))
				.as("Nested record patterns should bind components from both records")
				.isEqualTo("Rodolfo lives in Halifax, Canada");
		assertThat(examples.describe("not a customer"))
				.as("Non-matching values should use the fallback")
				.isEqualTo("unknown");
	}
}
