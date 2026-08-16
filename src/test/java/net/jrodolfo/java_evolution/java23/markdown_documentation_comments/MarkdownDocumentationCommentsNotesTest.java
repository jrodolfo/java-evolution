package net.jrodolfo.java_evolution.java23.markdown_documentation_comments;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MarkdownDocumentationCommentsNotesTest {

	private final MarkdownDocumentationCommentsNotes notes = new MarkdownDocumentationCommentsNotes();

	@Test
	void notesExplainMarkdownDocumentationComments() {
		String olderStyle = notes.olderStyle();
		String problemSolved = notes.problemSolved();
		String whatJavaIntroduced = notes.whatJavaIntroduced();
		String featureKind = notes.featureKind();
		String toolingResult = notes.toolingResult();

		assertThat(olderStyle)
				.as("The note should explain the older HTML-heavy JavaDoc style")
				.contains("HTML tags")
				.contains("lists")
				.contains("code blocks");
		assertThat(problemSolved)
				.as("Markdown comments solve the problem of noisy source documentation")
				.contains("HTML-heavy")
				.contains("noisy")
				.contains("source code");
		assertThat(whatJavaIntroduced)
				.as("The note should identify Markdown comments as the Java 23 source documentation style")
				.contains("Java 23")
				.contains("Markdown")
				.contains("code fences");
		assertThat(featureKind)
				.as("The note should make clear this is documentation/tooling, not runtime behavior")
				.contains("source documentation")
				.contains("JavaDoc tooling");
		assertThat(toolingResult)
				.as("Markdown documentation comments should still produce generated JavaDoc API documentation")
				.contains("JavaDoc tooling")
				.contains("generated API documentation");
	}
}
