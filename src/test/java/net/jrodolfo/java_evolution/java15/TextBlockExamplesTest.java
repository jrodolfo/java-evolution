package net.jrodolfo.java_evolution.java15;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TextBlockExamplesTest {

	private final TextBlockExamples examples = new TextBlockExamples();

	@Test
	void textBlockRepresentsStructuredMultilineText() {
		// When
		String json = examples.json();

		// Then
		assertThat(json)
				.as("Text blocks should preserve readable multi-line structured text")
				.contains("\"version\": 15")
				.contains("\"feature\": \"text blocks\"");
	}

	@Test
	void formattedCanFillTextBlockTemplateValues() {
		// When
		String summary = examples.formattedSummary(15, "text blocks");

		// Then
		assertThat(summary)
				.as("String.formatted should fill placeholders in a text block")
				.contains("Java 15")
				.contains("Feature: text blocks");
	}

	@Test
	void textBlockRemovesIncidentalIndentationButKeepsIntentionalIndentation() {
		// When
		String text = examples.textWithIncidentalIndentationRemoved();

		// Then
		assertThat(text)
				.as("The shared source indentation should be removed, but intentional indentation should remain")
				.isEqualTo("first\n  second\n");
	}
}
