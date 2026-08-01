package net.jrodolfo.java_evolution.java13;

/**
 * Demonstrates text blocks as a Java 13 preview feature.
 *
 * <p>
 * Text blocks became final later, in Java 15. This project compiles on JDK 25,
 * so the example uses final syntax while documenting the Java 13 preview
 * origin.
 * </p>
 */
public class TextBlockPreviewExamples {

	/**
	 * Uses a text block for readable multi-line JSON.
	 *
	 * @return JSON text
	 */
	public String jsonTextBlock() {
		return """
				{
				  "version": 13,
				  "feature": "text blocks"
				}
				""";
	}

	/**
	 * Uses a text block for readable SQL.
	 *
	 * @return SQL text
	 */
	public String sqlTextBlock() {
		return """
				select name
				from java_features
				where version = 13
				""";
	}
}
