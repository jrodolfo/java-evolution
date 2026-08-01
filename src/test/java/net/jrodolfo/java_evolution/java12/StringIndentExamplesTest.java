package net.jrodolfo.java_evolution.java12;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StringIndentExamplesTest {

	private final StringIndentExamples examples = new StringIndentExamples();

	@Test
	void indentAddsSpacesToEachLine() {
		// Given
		String text = "one\ntwo\n";

		// When
		String indented = examples.indent(text, 2);

		// Then
		assertThat(indented)
				.as("String.indent should add spaces to each line")
				.isEqualTo("  one\n  two\n");
	}

	@Test
	void negativeIndentRemovesSpacesFromEachLine() {
		// Given
		String text = "    one\n    two\n";

		// When
		String adjusted = examples.removeIndent(text, 2);

		// Then
		assertThat(adjusted)
				.as("String.indent with a negative value should remove indentation")
				.isEqualTo("  one\n  two\n");
	}
}
