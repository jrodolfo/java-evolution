package net.jrodolfo.java_evolution.java20;

/**
 * Demonstrates record patterns as refined in the Java 20 second preview.
 *
 * <p>
 * Record patterns reduce the gap between declaring transparent data with
 * records and extracting that data later. Java 20 refined the preview,
 * especially around nested record patterns, so code can match a structure and
 * bind its interesting pieces in one place.
 * </p>
 *
 * <p>
 * Record patterns became final in Java 21. This project compiles on JDK 26, so
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

	/**
	 * Customer record used to demonstrate nested record deconstruction.
	 *
	 * @param name customer name
	 * @param address customer address
	 */
	public record Customer(String name, Address address) {
	}

	/**
	 * Address record nested inside {@link Customer}.
	 *
	 * @param city city name
	 * @param country country name
	 */
	public record Address(String city, String country) {
	}
}
