package net.jrodolfo.java_evolution.java18;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CodeSnippetJavaDocNotesTest {

	private final CodeSnippetJavaDocNotes notes = new CodeSnippetJavaDocNotes();

	@Test
	void notesExplainJavadocSnippetTag() {
		// When / Then
		assertThat(notes.tagName())
				.as("The notes should name the JavaDoc snippet tag")
				.isEqualTo("@snippet");
		assertThat(notes.inlineSnippetExample())
				.as("The notes should show the inline snippet shape")
				.contains("{@snippet")
				.contains("var name");
		assertThat(notes.purpose())
				.as("The notes should explain why snippets are useful")
				.contains("code examples");
		assertThat(notes.detailedExplanation())
				.as("The notes should point learners to the executable JavaDoc snippets example")
				.endsWith("javadoc_snippets/README.md");
	}
}
