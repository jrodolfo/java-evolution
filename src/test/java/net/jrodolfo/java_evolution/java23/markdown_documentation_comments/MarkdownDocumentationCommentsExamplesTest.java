package net.jrodolfo.java_evolution.java23.markdown_documentation_comments;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class MarkdownDocumentationCommentsExamplesTest {

	private final MarkdownDocumentationCommentsExamples examples = new MarkdownDocumentationCommentsExamples();

	@Test
	void javadocRendersMarkdownDocumentationComments(@TempDir Path workspace) throws Exception {
		Path sourceFile = examples.createDocumentedSource(workspace.resolve("src"));
		Path documentationDirectory = workspace.resolve("docs");

		MarkdownDocumentationCommentsExamples.JavaDocResult result = examples.generateJavaDoc(sourceFile,
				documentationDirectory);

		assertThat(result.exitCode())
				.as("The child javadoc process should accept Markdown documentation comments")
				.isZero();

		Path generatedHtml = documentationDirectory.resolve("DocumentedAccount.html");
		assertThat(generatedHtml)
				.as("JavaDoc should generate an HTML page for the documented source type")
				.exists();

		String html = Files.readString(generatedHtml);
		assertThat(html)
				.as("Markdown headings should appear in generated API documentation")
				.contains("Documented Account")
				.contains("Rules");
		assertThat(html)
				.as("Markdown bullet items should be rendered into the generated API documentation")
				.contains("The owner must not be blank.")
				.contains("The account must stay active.");
		assertThat(html)
				.as("Inline Markdown code should be represented in generated documentation")
				.contains("owner");
		assertThat(result.output())
				.as("A successful JavaDoc run should not report an error")
				.doesNotContain("error:");
	}
}
