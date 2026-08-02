package net.jrodolfo.java_evolution.java16;

import java.util.List;

/**
 * Demonstrates {@link java.util.stream.Stream#toList()}, introduced in Java 16.
 */
public class StreamToListExamples {

	/**
	 * Collects stream results directly with {@code toList()}.
	 *
	 * @param names the names to normalize
	 * @return an unmodifiable list of normalized names
	 */
	public List<String> normalizedNames(List<String> names) {
		return names.stream()
				.map(String::strip)
				.map(String::toLowerCase)
				.toList();
	}
}
