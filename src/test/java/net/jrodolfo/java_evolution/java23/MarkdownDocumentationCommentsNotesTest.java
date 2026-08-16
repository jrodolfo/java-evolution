package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MarkdownDocumentationCommentsNotesTest {

	private final MarkdownDocumentationCommentsNotes notes = new MarkdownDocumentationCommentsNotes();

	@Test
	void notesExplainMarkdownDocumentationComments() {
		String before = notes.before();
		String purpose = notes.purpose();
		String after = notes.after();
		String benefit = notes.benefit();
		String toolingResult = notes.toolingResult();

		assertThat(before)
				.as("The note should explain the older HTML-heavy JavaDoc style")
				.contains("HTML tags")
				.contains("code blocks");
		assertThat(purpose)
				.as("Markdown comments solve the problem of HTML-heavy source documentation")
				.contains("Markdown");
		assertThat(after)
				.as("The note should identify Markdown comments as the Java 23 style")
				.contains("Java 23")
				.contains("Markdown");
		assertThat(benefit)
				.as("The note should explain why Markdown makes JavaDoc comments easier to maintain")
				.contains("easier to read");
		assertThat(toolingResult)
				.as("Markdown documentation comments should still produce generated JavaDoc API documentation")
				.contains("JavaDoc tooling")
				.contains("generated API documentation");
	}
}
