package net.jrodolfo.java_evolution.java14;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RecordPreviewExamplesTest {

	private final RecordPreviewExamples examples = new RecordPreviewExamples();

	@Test
	void recordCarriesDataWithGeneratedAccessors() {
		// When
		RecordPreviewExamples.Feature feature = examples.feature("records", true);

		// Then
		assertThat(feature.name())
				.as("A record should expose a generated accessor for each component")
				.isEqualTo("records");
		assertThat(feature.preview())
				.as("The preview component should also have a generated accessor")
				.isTrue();
	}

	@Test
	void recordHasGeneratedValueEquality() {
		// Given
		RecordPreviewExamples.Feature first = examples.feature("records", true);
		RecordPreviewExamples.Feature second = examples.feature("records", true);

		// Then
		assertThat(first)
				.as("Records should compare by component values")
				.isEqualTo(second);
		assertThat(examples.describe(first))
				.as("Record accessors can be used like ordinary methods")
				.isEqualTo("records preview=true");
	}
}
