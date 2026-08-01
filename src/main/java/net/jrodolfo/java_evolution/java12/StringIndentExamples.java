package net.jrodolfo.java_evolution.java12;

/**
 * Demonstrates {@link String#indent(int)}, introduced in Java 12.
 */
public class StringIndentExamples {

	/**
	 * Adds indentation to every line.
	 *
	 * @param text the text to indent
	 * @param spaces number of spaces to add
	 * @return indented text
	 */
	public String indent(String text, int spaces) {
		return text.indent(spaces);
	}

	/**
	 * Removes indentation from every line.
	 *
	 * @param text the text to adjust
	 * @param spaces number of spaces to remove
	 * @return text with indentation removed
	 */
	public String removeIndent(String text, int spaces) {
		return text.indent(-spaces);
	}
}
