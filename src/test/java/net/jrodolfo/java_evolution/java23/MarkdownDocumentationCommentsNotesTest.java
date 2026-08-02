package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MarkdownDocumentationCommentsNotesTest {

	private final MarkdownDocumentationCommentsNotes notes = new MarkdownDocumentationCommentsNotes();

	@Test
	void notesExplainMarkdownDocumentationComments() {
		assertThat(notes.purpose())
				.as("Markdown comments solve the problem of HTML-heavy source documentation")
				.contains("Markdown");
		assertThat(notes.benefit())
				.as("The note should explain why Markdown makes JavaDoc comments easier to maintain")
				.contains("easier to read");
	}
}
