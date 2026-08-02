package net.jrodolfo.java_evolution.java16;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class StreamToListExamplesTest {

	private final StreamToListExamples examples = new StreamToListExamples();

	@Test
	void streamToListCollectsResultsIntoUnmodifiableList() {
		// Given
		List<String> names = Arrays.asList(" Ana ", "RODOLFO");

		// When
		List<String> normalizedNames = examples.normalizedNames(names);

		// Then
		assertThat(normalizedNames)
				.as("Stream.toList should collect mapped values")
				.containsExactly("ana", "rodolfo");
		assertThatThrownBy(() -> normalizedNames.add("bruna"))
				.as("The list returned by Stream.toList should reject mutation")
				.isInstanceOf(UnsupportedOperationException.class);
	}
}
