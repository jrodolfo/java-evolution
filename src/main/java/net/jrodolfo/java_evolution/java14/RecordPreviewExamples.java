package net.jrodolfo.java_evolution.java14;

/**
 * Demonstrates records as a Java 14 preview feature.
 *
 * <p>
 * Records became final in Java 16. This project compiles on JDK 25, so this
 * class uses final record syntax while documenting that Java 14 was the first
 * preview release.
 * </p>
 */
public class RecordPreviewExamples {

	/**
	 * Creates a record value.
	 *
	 * @param name the feature name
	 * @param preview whether the feature was preview in Java 14
	 * @return a record containing both values
	 */
	public Feature feature(String name, boolean preview) {
		return new Feature(name, preview);
	}

	/**
	 * Formats a record using its generated accessor methods.
	 *
	 * @param feature the feature to describe
	 * @return a readable description
	 */
	public String describe(Feature feature) {
		return feature.name() + " preview=" + feature.preview();
	}

	/**
	 * Compact data carrier used to demonstrate record syntax.
	 *
	 * @param name the feature name
	 * @param preview whether it was preview in Java 14
	 */
	public record Feature(String name, boolean preview) {
	}
}
