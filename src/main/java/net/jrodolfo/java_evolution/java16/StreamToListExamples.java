package net.jrodolfo.java_evolution.java16;

import java.util.List;

/**
 * Demonstrates {@link java.util.stream.Stream#toList()}, introduced in Java 16.
 *
 * <p>
 * Before Java 16, the common way to collect stream elements into a list was
 * {@code collect(Collectors.toList())}. That is correct, but verbose for the
 * most common collection target.
 * </p>
 *
 * <p>
 * {@code Stream.toList()} solves this convenience problem with a direct
 * terminal operation. It returns an unmodifiable list.
 * </p>
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
