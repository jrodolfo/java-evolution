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
	 * Describes the older style this feature improves.
	 *
	 * @return a short before explanation
	 */
	public String before() {
		return "traditional JavaDoc comments often used HTML tags for lists, tables, and code blocks";
	}

	/**
	 * Explains the documentation problem addressed by the feature.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "write Java API documentation comments with Markdown instead of mostly HTML";
	}

	/**
	 * Describes the Java 23 style.
	 *
	 * @return a short after explanation
	 */
	public String after() {
		return "Java 23 lets documentation comments use Markdown for lighter source documentation";
	}

	/**
	 * Names the practical source-code benefit.
	 *
	 * @return a short benefit
	 */
	public String benefit() {
		return "documentation becomes easier to read in source code";
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
