package net.jrodolfo.java_evolution.java23.markdown_documentation_comments;

/**
 * Explains Markdown documentation comments, introduced in Java 23.
 *
 * <p>
 * Traditional JavaDoc comments often use HTML for structure such as lists and
 * code blocks. Java 23 allows API documentation comments to be written in
 * Markdown, giving developers a lighter alternative that is easier to read in
 * source code while still producing generated API documentation.
 * </p>
 */
public class MarkdownDocumentationCommentsNotes {

	/**
	 * Describes the older style this feature improves.
	 *
	 * @return a short before explanation
	 */
	public String olderStyle() {
		return "traditional JavaDoc comments often used HTML tags for lists, paragraphs, and code blocks";
	}

	/**
	 * Explains the documentation problem addressed by the feature.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "HTML-heavy documentation comments can be noisy to read and maintain in source code";
	}

	/**
	 * Describes the Java 23 style.
	 *
	 * @return a short after explanation
	 */
	public String whatJavaIntroduced() {
		return "Java 23 lets API documentation comments use Markdown for lists, links, and code fences";
	}

	/**
	 * Explains what kind of feature this is.
	 *
	 * @return a short classification
	 */
	public String featureKind() {
		return "Markdown documentation comments are a source documentation and JavaDoc tooling feature";
	}

	/**
	 * Explains what still produces the generated API documentation.
	 *
	 * @return a short tooling note
	 */
	public String toolingResult() {
		return "JavaDoc tooling still turns documentation comments into generated API documentation";
	}
}
