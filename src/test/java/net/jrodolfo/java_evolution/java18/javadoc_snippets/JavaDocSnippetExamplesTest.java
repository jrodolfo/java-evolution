package net.jrodolfo.java_evolution.java18.javadoc_snippets;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class JavaDocSnippetExamplesTest {

	private final JavaDocSnippetExamples examples = new JavaDocSnippetExamples();

	@Test
	void normalizesTitleForDocumentationDisplay() {
		var title = examples.normalizeTitle("  java   evolution  ");

		assertThat(title)
				.as("The documented title example should trim, collapse, and capitalize words")
				.isEqualTo("Java Evolution");
	}

	@Test
	void returnsLimitedPreviewLines() {
		var preview = examples.previewLines(List.of("install jdk", "run tests", "read javadocs"), 2);

		assertThat(preview)
				.as("The preview snippet should keep only the requested number of lines")
				.containsExactly("install jdk", "run tests");
	}

	@Test
	void returnsEmptyPreviewWhenLimitIsNotPositive() {
		var preview = examples.previewLines(List.of("install jdk"), 0);

		assertThat(preview)
				.as("A non-positive preview limit should avoid surprising output")
				.isEmpty();
	}

	@Test
	void formatsCommandShownInDocumentation() {
		var command = examples.formatCommand("jwebserver", 8000);

		assertThat(command)
				.as("The command snippet should match the Java 18 Simple Web Server style")
				.isEqualTo("jwebserver --port 8000");
	}
}
