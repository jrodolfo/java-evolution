package net.jrodolfo.java_evolution.java15;

/**
 * Demonstrates text blocks, finalized in Java 15.
 *
 * <p>
 * Text blocks make multi-line strings easier to read and maintain, especially
 * for JSON, SQL, HTML, and other structured text.
 * </p>
 */
public class TextBlockExamples {

	/**
	 * Creates JSON using a text block.
	 *
	 * @return multi-line JSON text
	 */
	public String json() {
		return """
				{
				  "version": 15,
				  "feature": "text blocks"
				}
				""";
	}

	/**
	 * Uses {@link String#formatted(Object...)} with a text block template.
	 *
	 * @param version the Java version
	 * @param feature the feature name
	 * @return formatted multi-line text
	 */
	public String formattedSummary(int version, String feature) {
		return """
				Java %d
				Feature: %s
				""".formatted(version, feature);
	}
}
