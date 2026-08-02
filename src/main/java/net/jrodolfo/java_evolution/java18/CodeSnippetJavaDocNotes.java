package net.jrodolfo.java_evolution.java18;

/**
 * Explains JavaDoc code snippets introduced in Java 18.
 *
 * <p>
 * Before Java 18, JavaDoc examples often used plain {@code <pre>} blocks.
 * Those examples were readable, but the documentation tool treated them mostly
 * as formatted text. Java 18 added the {@code @snippet} tag so documentation
 * can include code examples in a more structured way.
 * </p>
 *
 * <p>
 * This matters in a learning project because code examples in documentation
 * should be easy to format consistently and easy to scan in the generated
 * JavaDoc site.
 * </p>
 */
public class CodeSnippetJavaDocNotes {

	/**
	 * Names the JavaDoc tag.
	 *
	 * @return the tag name
	 */
	public String tagName() {
		return "@snippet";
	}

	/**
	 * Shows a small inline snippet.
	 *
	 * @return snippet text that can appear in JavaDoc
	 */
	public String inlineSnippetExample() {
		return "{@snippet :\nvar name = \"Java 18\";\n}";
	}

	/**
	 * Explains why snippets matter.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "document code examples with structure instead of relying only on preformatted text";
	}
}
