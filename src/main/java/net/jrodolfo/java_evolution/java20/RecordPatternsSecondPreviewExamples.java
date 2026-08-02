package net.jrodolfo.java_evolution.java20;

/**
 * Demonstrates record patterns as refined in the Java 20 second preview.
 *
 * <p>
 * Record patterns became final in Java 21. This project compiles on JDK 25, so
 * the example uses final syntax while documenting the Java 20 preview status.
 * </p>
 */
public class RecordPatternsSecondPreviewExamples {

	/**
	 * Deconstructs nested records with record patterns.
	 *
	 * @param value the value to inspect
	 * @return a formatted address label
	 */
	public String describe(Object value) {
		if (value instanceof Customer(String name, Address(String city, String country))) {
			return name + " lives in " + city + ", " + country;
		}
		return "unknown";
	}

	public record Customer(String name, Address address) {
	}

	public record Address(String city, String country) {
	}
}
