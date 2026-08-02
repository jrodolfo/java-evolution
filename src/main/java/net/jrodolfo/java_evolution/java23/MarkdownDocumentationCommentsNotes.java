package net.jrodolfo.java_evolution.java23;

/**
 * Explains Markdown documentation comments, introduced in Java 23.
 *
 * <p>
 * Traditional JavaDoc comments often use HTML for structure such as lists and
 * code blocks. Java 23 allows API documentation comments to be written in
 * Markdown, giving developers a lighter alternative that is easier to read in
 * source code.
 * </p>
 */
public class MarkdownDocumentationCommentsNotes {

	/**
	 * Explains the documentation problem addressed by the feature.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "write Java API documentation comments with Markdown instead of mostly HTML";
	}

	/**
	 * Names the practical source-code benefit.
	 *
	 * @return a short benefit
	 */
	public String benefit() {
		return "documentation becomes easier to read in source code";
	}
}
