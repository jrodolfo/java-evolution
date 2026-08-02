package net.jrodolfo.java_evolution.java15;

/**
 * Demonstrates text blocks, finalized in Java 15.
 *
 * <p>
 * Before text blocks, multi-line strings required newline escapes,
 * concatenation, and careful indentation. That made embedded JSON, SQL, HTML,
 * and other structured text hard to read in Java source code.
 * </p>
 *
 * <p>
 * Text blocks solve this by making multi-line string literals readable and
 * maintainable. Java 15 finalized the feature after previews in Java 13 and
 * Java 14.
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
