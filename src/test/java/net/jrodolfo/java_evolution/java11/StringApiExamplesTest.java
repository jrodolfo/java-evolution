package net.jrodolfo.java_evolution.java11;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class StringApiExamplesTest {

	private final StringApiExamples examples = new StringApiExamples();

	@Test
	void isBlankDetectsWhitespaceOnlyText() {
		// When / Then
		assertThat(examples.isBlankText(" \t\n"))
				.as("String.isBlank should treat whitespace-only text as blank")
				.isTrue();
		assertThat(examples.isBlankText("Java 11"))
				.as("String.isBlank should return false when visible characters exist")
				.isFalse();
	}

	@Test
	void linesCreatesAStreamOfTextLines() {
		// Given
		String text = "first\nsecond\nthird";

		// When
		List<String> lines = examples.lines(text);

		// Then
		assertThat(lines)
				.as("String.lines should expose each line in encounter order")
				.containsExactly("first", "second", "third");
	}

	@Test
	void stripRemovesLeadingAndTrailingWhitespace() {
		// When
		String stripped = examples.stripText("  Java 11  ");

		// Then
		assertThat(stripped)
				.as("String.strip should remove surrounding whitespace")
				.isEqualTo("Java 11");
	}

	@Test
	void stripLeadingRemovesOnlyLeadingWhitespace() {
		// When
		String stripped = examples.stripLeadingText("  Java 11  ");

		// Then
		assertThat(stripped)
				.as("String.stripLeading should preserve trailing whitespace")
				.isEqualTo("Java 11  ");
	}

	@Test
	void stripTrailingRemovesOnlyTrailingWhitespace() {
		// When
		String stripped = examples.stripTrailingText("  Java 11  ");

		// Then
		assertThat(stripped)
				.as("String.stripTrailing should preserve leading whitespace")
				.isEqualTo("  Java 11");
	}

	@Test
	void repeatCreatesRepeatedText() {
		// When
		String repeated = examples.repeatText("ha", 3);

		// Then
		assertThat(repeated)
				.as("String.repeat should concatenate the text the requested number of times")
				.isEqualTo("hahaha");
	}
}
