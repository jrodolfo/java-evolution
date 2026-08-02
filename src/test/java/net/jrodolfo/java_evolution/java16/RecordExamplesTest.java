package net.jrodolfo.java_evolution.java16;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class RecordExamplesTest {

	private final RecordExamples examples = new RecordExamples();

	@Test
	void recordProvidesGeneratedAccessorsAndValueEquality() {
		// Given
		RecordExamples.Feature first = examples.feature("records", 16);
		RecordExamples.Feature second = examples.feature("records", 16);

		// Then
		assertThat(first.name())
				.as("A record should generate an accessor for each component")
				.isEqualTo("records");
		assertThat(first)
				.as("A record should implement value-based equality")
				.isEqualTo(second);
		assertThat(first.toString())
				.as("A record should generate a useful toString")
				.contains("records")
				.contains("16");
	}

	@Test
	void compactConstructorValidatesRecordComponents() {
		// When / Then
		assertThat(examples.release("Java", 16).version())
				.as("Valid record data should be accepted by the compact constructor")
				.isEqualTo(16);
		assertThatThrownBy(() -> examples.release("", 16))
				.as("The compact constructor should reject blank names")
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("name is required");
	}
}
