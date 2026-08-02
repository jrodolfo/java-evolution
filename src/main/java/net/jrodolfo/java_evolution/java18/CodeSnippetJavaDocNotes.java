package net.jrodolfo.java_evolution.java18;

/**
 * Explains JavaDoc code snippets introduced in Java 18.
 *
 * <p>
 * Java 18 added the {@code @snippet} tag so documentation can include code
 * examples in a structured way.
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
