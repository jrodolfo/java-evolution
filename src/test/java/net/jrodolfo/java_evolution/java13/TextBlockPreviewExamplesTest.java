package net.jrodolfo.java_evolution.java13;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TextBlockPreviewExamplesTest {

	private final TextBlockPreviewExamples examples = new TextBlockPreviewExamples();

	@Test
	void textBlockKeepsJsonReadableInSourceCode() {
		// When
		String json = examples.jsonTextBlock();

		// Then
		assertThat(json)
				.as("A text block should preserve readable multi-line JSON")
				.contains("\"version\": 13")
				.contains("\"feature\": \"text blocks\"");
	}

	@Test
	void textBlockKeepsSqlReadableInSourceCode() {
		// When
		String sql = examples.sqlTextBlock();

		// Then
		assertThat(sql)
				.as("A text block should preserve readable multi-line SQL")
				.contains("select name")
				.contains("where version = 13");
	}
}
